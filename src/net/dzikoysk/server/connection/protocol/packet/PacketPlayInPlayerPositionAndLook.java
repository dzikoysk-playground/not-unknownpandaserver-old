package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;
import net.dzikoysk.server.world.Location;

public class PacketPlayInPlayerPositionAndLook extends Packet {
	
	private double x, y, z;
	private float yaw, pitch;
	private boolean onGround;
	
	@Override
	public void receive(DataSerializer data){
		x = data.readDouble();
		y = data.readDouble();
		z = data.readDouble();
		yaw = data.readFloat();
		pitch = data.readFloat();
		onGround = data.readBoolean();
	}
	
	@Override
	public PacketInfo getPacketInfo(){
		return PacketInfo.PLAY_IN_PLAYER_POSITION_AND_LOOK;
	}

	public Location getLocation(){
		return new Location(x, y, z, yaw, pitch);
	}
	
	public boolean isOnGround(){
		return onGround;
	}
	
}
