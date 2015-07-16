package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;

public class PacketPlayInPlayer extends Packet {

	private boolean ground;
	
	@Override
	public void receive(DataSerializer data){
		ground = data.readBoolean();
	}
	
	@Override
	public PacketInfo getPacketInfo(){
		return PacketInfo.PLAY_IN_PLAYER;
	}
	
	public boolean isOnGround(){
		return ground;
	}
	
}
