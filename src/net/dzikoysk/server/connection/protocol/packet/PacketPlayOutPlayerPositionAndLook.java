package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;
import net.dzikoysk.server.world.Location;

public class PacketPlayOutPlayerPositionAndLook extends Packet {
	
	private double x, y, z;
	private float yaw, pitch;
	
	public PacketPlayOutPlayerPositionAndLook(Location location){
		x = location.getX();
		y = location.getY();
		z = location.getZ();
		yaw = location.getYaw();
		pitch = location.getPitch();
	}
	
	@Override
	public void send(DataSerializer data){
		data.writeDouble(x);
		data.writeDouble(y);
		data.writeDouble(z);
		data.writeFloat(yaw);
		data.writeFloat(pitch);
		data.writeByte(0x02);
	}
	
	@Override
	public PacketInfo getPacketInfo(){
		return PacketInfo.PLAY_OUT_PLAYER_POSITION_AND_LOOK;
	}

}
