package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataDecoder;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;

public class PacketPlayInEntityAction extends Packet {
	
	private int eID, aID, boost;
	
	@Override
	public void receive(DataSerializer data){
		eID = DataDecoder.decodeInt(data);
		aID = DataDecoder.decodeInt(data);
		boost = DataDecoder.decodeInt(data);
	}
	
	@Override
	public PacketInfo getPacketInfo(){
		return PacketInfo.PLAY_IN_ENTITY_ACTION;
	}
	
	public int getEntityID(){
		return eID;
	}
	
	public int getActionID(){
		return aID;
	}
	
	public int getJumpBoost(){
		return boost;
	}

}
