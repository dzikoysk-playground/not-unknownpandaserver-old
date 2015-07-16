package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;
import net.dzikoysk.server.entity.EntityAbilities;

public class PacketPlayOutPlayerAbilities extends Packet {
	
	private final EntityAbilities ability;
	
	public PacketPlayOutPlayerAbilities(EntityAbilities ability){
		this.ability = ability;
	}
	
	@Override
	public void send(DataSerializer data){
		data.writeByte(1 | ability.getFlag());
		data.writeFloat(0.05f);
		data.writeFloat(0.1f);
	}
	
	@Override
	public PacketInfo getPacketInfo(){
		return PacketInfo.PLAY_OUT_PLAYER_ABILITIES;
	}

}
