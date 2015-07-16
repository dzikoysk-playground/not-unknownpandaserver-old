package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataEncoder;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;
import net.dzikoysk.server.world.Location;

public class PacketPlayOutSpawnPosition extends Packet {

	private final Location location;
	
	public PacketPlayOutSpawnPosition(Location location){
		this.location = location;
	}
	
	@Override
	public void send(DataSerializer data){
		DataEncoder.encodeLocation(data, location);
	}
	
	@Override
	public PacketInfo getPacketInfo(){
		return PacketInfo.PLAY_OUT_SPAWN_POSITION;
	}
	
}
