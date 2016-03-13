package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataDecoder;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;

@SuppressWarnings("unused")
public class PacketPlayInClientSettings extends Packet {
	
	private String locale;
	private byte view, flags;
	private boolean colours;
	private int skinParts;
	
	@Override
	public void receive(DataSerializer data){
		locale = DataDecoder.decodeString(data);
		view = data.readByte();
		flags = data.readByte();
		colours = data.readBoolean();
		skinParts = data.readUnsignedByte();
	}

	@Override
	public PacketInfo getPacketInfo(){
		return PacketInfo.PLAY_IN_CLIENT_SETTINGS;
	}
	
}
