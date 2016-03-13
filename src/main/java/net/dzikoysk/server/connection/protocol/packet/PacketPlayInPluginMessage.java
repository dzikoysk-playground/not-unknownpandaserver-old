package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataDecoder;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;

@SuppressWarnings("unused")
public class PacketPlayInPluginMessage extends Packet {

    private String channel;
    private byte[] bytes;

    @Override
    public void receive(DataSerializer data) {
        channel = DataDecoder.decodeString(data);
        int av = data.available();
        bytes = new byte[av];
        for (int i = 0; i < av; i++) {
            bytes[i] = data.readByte();
        }
    }

    @Override
    public PacketInfo getPacketInfo() {
        return PacketInfo.PLAY_IN_PLUGIN_MESSAGE;
    }

}
