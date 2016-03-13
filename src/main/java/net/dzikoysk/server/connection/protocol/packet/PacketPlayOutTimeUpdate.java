package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;

public class PacketPlayOutTimeUpdate extends Packet {

	@Override
	public void send(DataSerializer data){
		data.writeLong(0L);
		data.writeLong(0L);
	}
	
	@Override
	public PacketInfo getPacketInfo(){
		return PacketInfo.PLAY_OUT_TIME_UPDATE;
	}
	
}
