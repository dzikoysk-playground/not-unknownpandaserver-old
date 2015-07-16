package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataDecoder;
import net.dzikoysk.server.connection.protocol.util.DataEncoder;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;

public class PacketPlayIOKeepAlive extends Packet {
	
	private int id;
	
	@Override
	public void receive(DataSerializer data){
		id = DataDecoder.decodeInt(data);
	}
	
	@Override
	public void send(DataSerializer data){
		DataEncoder.encodeInt(data, id);
	}

	@Override
	public PacketInfo getPacketInfo(){
		return PacketInfo.PLAY_IO_KEEP_ALIVE;
	}
	
	public int getRandomID(){
		return this.id;
	}
	
}
