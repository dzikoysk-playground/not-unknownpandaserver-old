package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.packet.util.TitleAction;
import net.dzikoysk.server.connection.protocol.util.DataEncoder;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;

public class PacketPlayOutTitle extends Packet {

	private final TitleAction action;

	public PacketPlayOutTitle(TitleAction action){
		this.action = action;
	}

	@Override
	public void send(DataSerializer data){
		DataEncoder.encodeInt(data, action.getID());
	}



}
