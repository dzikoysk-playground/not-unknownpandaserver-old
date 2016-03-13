package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataDecoder;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;

public class PacketLoginInStart extends Packet {
	
	private String username;
	
	@Override
	public void receive(DataSerializer data){
		username = DataDecoder.decodeString(data);
	}
	
	@Override
	public PacketInfo getPacketInfo(){
		return PacketInfo.LOGIN_IN_START;
	}
	
	public String getUsername(){
		return username;
	}

}
