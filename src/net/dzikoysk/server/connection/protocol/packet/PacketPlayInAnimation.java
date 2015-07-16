package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;

public class PacketPlayInAnimation extends Packet {

	@Override
	public PacketInfo getPacketInfo(){
		return PacketInfo.PLAY_IN_ANIMATION;
	}
	
}
