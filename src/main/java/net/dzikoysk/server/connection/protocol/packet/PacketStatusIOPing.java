package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;

public class PacketStatusIOPing extends Packet {
	
	private long time;
	
	public PacketStatusIOPing(long time){
		this.time = time;
	}
	
	public PacketStatusIOPing(){
	}
	
	@Override
	public void receive(DataSerializer data){
		this.time = data.readLong();
	}
	
	@Override
	public void send(DataSerializer data){
		data.writeLong(System.currentTimeMillis() - this.time);
	}
	
	@Override
	public PacketInfo getPacketInfo(){
		return PacketInfo.STATUS_IO_PING;
	}

	public long getTime(){
		return this.time;
	}
	
}
