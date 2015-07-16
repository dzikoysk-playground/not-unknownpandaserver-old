package net.dzikoysk.server.connection.protocol;

import net.dzikoysk.server.connection.protocol.util.DataSerializer;

public class Packet {
	
	public void receive(DataSerializer data){
		for(int i = 0; i < data.available(); i++)
			data.read();
	}
	
	public void send(DataSerializer data){
	}
	
	public PacketInfo getPacketInfo(){
		return null;
	}
	
}
