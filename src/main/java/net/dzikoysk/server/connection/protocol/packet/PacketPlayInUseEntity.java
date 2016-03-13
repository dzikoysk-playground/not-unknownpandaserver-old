package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataDecoder;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;
import net.dzikoysk.server.entity.EntityUse;
import net.dzikoysk.server.world.Location;

public class PacketPlayInUseEntity extends Packet {

	private int target;
	private int type;
	private float x, y, z;
	
	@Override
	public void receive(DataSerializer data){
		target = DataDecoder.decodeInt(data);
		type = DataDecoder.decodeInt(data);
		if(type == 2){
			x = data.readFloat();
			y = data.readFloat();
			z = data.readFloat();
		}
	}
	
	@Override
	public PacketInfo getPacketInfo(){
		return PacketInfo.PLAY_IN_USE_ENTITY;
	}
	
	public Location getLocation(){
		return new Location(x, y, z);
	}
	
	public EntityUse getType(){
		return EntityUse.getType(type);
	}
	
	public int getEntityID(){
		return target;
	}
	
}
