package net.dzikoysk.server.module;

import net.dzikoysk.server.UnknownPandaServer;
import net.dzikoysk.server.connection.protocol.packet.PacketPlayOutChatMessage;
import net.dzikoysk.server.entity.Player;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

public class ChatModule extends Thread {

    private static ChatModule instance;
    private Collection<String> messages;
    private Object locker;

    public ChatModule() {
        messages = new LinkedList<>();
        locker = new Object();
        instance = this;
    }

    public static void receive(String message) {
        instance.messages.add(message);
        synchronized (instance.locker) {
            instance.locker.notify();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                distribute();
                messages.clear();
                synchronized (locker) {
                    locker.wait();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void distribute() {
        for (String message : messages) {
            PacketPlayOutChatMessage packet = new PacketPlayOutChatMessage(message);
            for (Player player : UnknownPandaServer.getOnlinePlayers()) {
                try {
                    player.getPlayerConnection().sendPacket(packet);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
