package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataDecoder;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;

public class PacketHandshakingInSetProtocol extends Packet {

    private int protocolVersion;
    private String serverHost;
    private int serverPort;
    private int nextState;

    @Override
    public void receive(DataSerializer data) {
        try {
            protocolVersion = DataDecoder.decodeInt(data);
            serverHost = DataDecoder.decodeString(data);
            serverPort = data.readUnsignedShort();
            nextState = DataDecoder.decodeInt(data);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public PacketInfo getPacketInfo() {
        return PacketInfo.HANDSHAKING_IN_SET_PROTOCOL;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public String getServerHost() {
        return serverHost;
    }

    public int getServerPort() {
        return serverPort;
    }

    public int getNextState() {
        return nextState;
    }

}
