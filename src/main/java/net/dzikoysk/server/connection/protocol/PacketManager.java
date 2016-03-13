package net.dzikoysk.server.connection.protocol;

import net.dzikoysk.server.UnknownPandaServer;
import net.dzikoysk.server.connection.PlayerConnection;
import net.dzikoysk.server.connection.protocol.packet.*;
import net.dzikoysk.server.connection.protocol.packet.util.KeepAliveTask;
import net.dzikoysk.server.entity.EntityAbilities;
import net.dzikoysk.server.entity.Player;
import net.dzikoysk.server.module.ChatModule;
import net.dzikoysk.server.world.Location;

import java.io.IOException;

public class PacketManager {

	private final PlayerConnection connection;
	private ProtocolState state = ProtocolState.HANDSHAKING;
	private Player player;
	private boolean play;
	
	public PacketManager(PlayerConnection connection){
		this.connection = connection;
	}
	
	public void receive(Packet packet) throws IOException {
		switch(packet.getPacketInfo()){
			case HANDSHAKING_IN_SET_PROTOCOL:
				PacketHandshakingInSetProtocol p = (PacketHandshakingInSetProtocol) packet;
				state = ProtocolState.getProtocolState(p.getNextState());
				break;
			case STATUS_IN_REQUEST:
				connection.sendPacket(new PacketStatusOutServerInfo());
				connection.sendPacket(new PacketStatusIOPing(System.currentTimeMillis()));
				break;
			case LOGIN_IN_START:
				player = new Player(connection, ((PacketLoginInStart) packet).getUsername());
				player.setLocation(new Location(0, 17, 0));
				connection.setPlayer(player);
				connection.sendPacket(new PacketLoginOutSuccess(player));
				connection.sendPacket(new PacketPlayOutJoin());
				state = ProtocolState.PLAY;
				break;
			case PLAY_IN_PLUGIN_MESSAGE:
				connection.sendPacket(new PacketPlayOutSpawnPosition(UnknownPandaServer.getWorld().getSpawn()));
				connection.sendPacket(new PacketPlayOutPlayerAbilities(EntityAbilities.FLY));
				connection.sendPacket(new PacketPlayOutPlayerPositionAndLook(player.getLocation()));
				break;
			case PLAY_IN_PLAYER_POSITION_AND_LOOK:
				if(play){
					player.setLocation(((PacketPlayInPlayerPositionAndLook) packet).getLocation());
					break;
				}
				play = !play;
				new KeepAliveTask(connection).start();
				connection.ready();
				connection.setWorldProvier(new WorldProvider(player, UnknownPandaServer.getWorld()));
				connection.sendPacket(new PacketPlayOutTimeUpdate());
				connection.sendPacket(new PacketPlayOutChatMessage("UnknownPandaServer " + UnknownPandaServer.VERSION, 2));
				break;
			case PLAY_IN_PLAYER_POSITION:
				player.setLocation(((PacketPlayInPlayerPosition) packet).getLocation());
				break;
			case PLAY_IN_PLAYER_LOOK:
				break;
			case PLAY_IN_CHAT_MESSAGE:
				String message = ((PacketPlayInChatMessage) packet).getMessage();
				ChatModule.receive(player.getName() + ": " + message);
			default:
				break;
		}
	}
	
	public ProtocolState getProtocolState(){
		return state;
	}
	
}
