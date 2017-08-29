package net.dzikoysk.server.connection.protocol.packet.util;

import net.dzikoysk.server.connection.PlayerConnection;
import net.dzikoysk.server.connection.protocol.packet.PacketPlayIOKeepAlive;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class KeepAliveTask extends TimerTask {

    private final PlayerConnection connection;
    private final Timer timer;

    public KeepAliveTask(PlayerConnection connection) {
        this.connection = connection;
        this.timer = new Timer();
    }

    public void start() {
        timer.schedule(this, 0, 1000);
    }

    public void stop() {
        timer.cancel();
    }

    @Override
    public void run() {
        try {
            connection.sendPacket(new PacketPlayIOKeepAlive());
        } catch (IOException e) {
            stop();
        }
    }

}
