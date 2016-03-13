package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataDecoder;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;

public class PacketPlayInChatMessage extends Packet {

    private String message;

    @Override
    public void receive(DataSerializer data) {
        message = DataDecoder.decodeString(data);
    }

    @Override
    public PacketInfo getPacketInfo() {
        return PacketInfo.PLAY_IN_CHAT_MESSAGE;
    }

    public String getMessage() {
        return this.message;
    }

}
