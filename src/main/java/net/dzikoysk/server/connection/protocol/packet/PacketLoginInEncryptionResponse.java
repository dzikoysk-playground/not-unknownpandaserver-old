package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;

public class PacketLoginInEncryptionResponse extends Packet {
	
	@Override
	public PacketInfo getPacketInfo(){
		return PacketInfo.LOGIN_IN_ENCRYPTION_RESPONSE;
	}

}
