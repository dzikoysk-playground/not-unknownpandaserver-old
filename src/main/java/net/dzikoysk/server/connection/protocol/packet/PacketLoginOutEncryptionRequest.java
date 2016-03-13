package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataEncoder;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;

public class PacketLoginOutEncryptionRequest extends Packet {
	
	@Override
	public void send(DataSerializer data){
		DataEncoder.encodeString(data, "");
	}
	
	@Override
	public PacketInfo getPacketInfo(){
		return PacketInfo.LOGIN_OUT_ENCRYPTION_REQUEST;
	}

}
