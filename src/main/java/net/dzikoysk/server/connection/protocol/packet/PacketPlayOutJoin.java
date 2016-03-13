package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataEncoder;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;

public class PacketPlayOutJoin extends Packet {

    @Override
    public void send(DataSerializer data) {
        data.writeInt(1);
        data.writeByte((byte) 0x01); // for creative
        data.writeByte((byte) 0x00); // for overworld
        data.writeByte((byte) 0x00); // for peaceful
        data.writeByte((byte) 0x25); // for 25 players (tab size)
        DataEncoder.encodeString(data, "flat");
        data.writeBoolean(false); // reduced debug
    }

    @Override
    public PacketInfo getPacketInfo() {
        return PacketInfo.PLAY_OUT_JOIN;
    }

}
