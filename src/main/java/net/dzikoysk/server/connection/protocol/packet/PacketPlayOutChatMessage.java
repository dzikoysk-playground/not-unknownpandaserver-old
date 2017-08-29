package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.packet.util.ChatFormat;
import net.dzikoysk.server.connection.protocol.util.DataEncoder;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;
import net.dzikoysk.server.element.ChatColor;

public class PacketPlayOutChatMessage extends Packet {

    private String message;
    private int position;

    public PacketPlayOutChatMessage(String message) {
        this.message = message;
    }

    public PacketPlayOutChatMessage(String message, int position) {
        this.message = message;
        this.position = position;
    }

    public PacketPlayOutChatMessage(String message, boolean colored) {
        this.message = ChatColor.translate('&', message);
    }

    public PacketPlayOutChatMessage(String message, int position, boolean colored) {
        this.message = ChatColor.translate('&', message);
        this.position = position;
    }

    @Override
    public void send(DataSerializer data) {
        DataEncoder.encodeString(data, ChatFormat.getPlainText(message));
        data.writeByte(position);
    }

    @Override
    public PacketInfo getPacketInfo() {
        return PacketInfo.PLAY_OUT_CHAT_MESSAGE;
    }

    public String getMessage() {
        return message;
    }

}
