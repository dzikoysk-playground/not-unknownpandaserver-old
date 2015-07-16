package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;

public class PacketStatusInRequest extends Packet {
	
	@Override
	public PacketInfo getPacketInfo(){
		return PacketInfo.STATUS_IN_REQUEST;
	}

}
