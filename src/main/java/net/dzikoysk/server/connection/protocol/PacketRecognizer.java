package net.dzikoysk.server.connection.protocol;

import net.dzikoysk.server.UnknownPandaServer;
import net.dzikoysk.server.connection.PlayerConnection;
import net.dzikoysk.server.connection.protocol.packet.PacketPlayOutChatMessage;
import net.dzikoysk.server.connection.protocol.util.DataDecoder;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;

public class PacketRecognizer {

	private final PlayerConnection connection;
	private final PacketManager manager;
	
	public PacketRecognizer(PlayerConnection connection){
		this.connection = connection;
		this.manager = connection.getProtocolManager();
	}
	
	public boolean receive(DataSerializer data) throws Exception {
		Packet packet = identify(data, manager.getProtocolState());
		if(packet == null) return false;
		manager.receive(packet);
		return true;
	}
	
	public Packet identify(DataSerializer data, ProtocolState state) throws Exception {
		//Check connection
		int k = 0;
		try {
			k = connection.isConnected() ? data.getInputStream().read() : -1;
			if (k == -1) {
				connection.getConnection().disconnect(0);
				return null;
			}
		} catch (Exception e){
			connection.getConnection().disconnect(0);
			return null;
		}

		//System.out.println("------ new Packet ------");
		int length = DataDecoder.decodeInt(k);
		//System.out.println("Length: " + length);
		DataSerializer serializer = data.partOfData(length);

		int packetID = DataDecoder.decodeInt(serializer);
		//System.out.println("PacketID: 0x" + Integer.toHexString(packetID));
		
		PacketInfo pi = PacketInfo.getPacket(packetID, state, ProtocolBound.SERVER);
		if(pi == null){
			UnknownPandaServer.getLogger().info("------ new UnknownPacket ------");
			UnknownPandaServer.getLogger().info("Length: " + length);
			UnknownPandaServer.getLogger().info("PacketID: 0x" + Integer.toHexString(packetID));
			connection.sendPacket(new PacketPlayOutChatMessage("[0x" + Integer.toHexString(packetID) + "] Nieznany packet"));
			return null;
		}
		Packet packet = pi.getPacket();
		packet.receive(serializer);
		return packet;
	}

}
