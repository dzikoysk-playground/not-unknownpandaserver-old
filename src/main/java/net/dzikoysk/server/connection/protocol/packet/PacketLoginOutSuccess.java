package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataEncoder;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;
import net.dzikoysk.server.entity.Player;

public class PacketLoginOutSuccess extends Packet {

    private final Player player;

    public PacketLoginOutSuccess(Player player) {
        this.player = player;
    }

    @Override
    public void send(DataSerializer data) {
        DataEncoder.encodeString(data, player.getUUID().toString());
        DataEncoder.encodeString(data, player.getName());
    }

    @Override
    public PacketInfo getPacketInfo() {
        return PacketInfo.LOGIN_OUT_SUCCESS;
    }

    public Player getPlayer() {
        return player;
    }

}
