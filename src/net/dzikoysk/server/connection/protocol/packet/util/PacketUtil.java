package net.dzikoysk.server.connection.protocol.packet.util;

import net.dzikoysk.server.UnknownPandaServer;
import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.entity.Player;

import java.io.IOException;

public class PacketUtil {

	public static void distributeAll(Packet packet){
		for(Player player : UnknownPandaServer.getOnlinePlayers())
			try {
				player.getPlayerConnection().sendPacket(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
