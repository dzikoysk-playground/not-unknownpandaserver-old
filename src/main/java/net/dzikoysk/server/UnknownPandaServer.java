package net.dzikoysk.server;

import net.dzikoysk.server.connection.protocol.ProtocolVersion;
import net.dzikoysk.server.connection.protocol.packet.PacketPlayOutChatMessage;
import net.dzikoysk.server.connection.protocol.packet.util.PacketUtil;
import net.dzikoysk.server.element.ChatColor;
import net.dzikoysk.server.entity.Player;
import net.dzikoysk.server.logging.Logger;
import net.dzikoysk.server.world.World;

import java.util.Collection;

public class UnknownPandaServer {
	
	public static final String VERSION = "1.0.0";

	private static Logger logger;
	private static ServerConfiguration configuration;
	private static MinecraftServer server;

	private static ProtocolVersion protocol;
	private static String host;
	private static int port;
	
	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();

		logger = new Logger();
		configuration = new ServerConfiguration();
		protocol = ProtocolVersion.getProtocolVersion(configuration.getInteger("protocol"));
		host = configuration.getString("host");
		port = configuration.getInteger("port");

		getLogger().info("Starting minecraft server on *:" + port);
		getLogger().info("Server's protocol number '" + protocol.getNumber() + "' for version " + protocol.getName());
		server = new MinecraftServer(port);

		Thread.currentThread().setName("UnknownPandaServer - Main Thread");
		getLogger().info("Done (" + (System.currentTimeMillis() - start) + "ms)!");
	}

	public static void broadcastMessage(String message){
		PacketUtil.distributeAll(new PacketPlayOutChatMessage(message));
		getLogger().info(message);
	}

	public static void broadcastColoredMessage(String message){
		broadcastMessage(ChatColor.translate(message));
	}

	public static Logger getLogger(){
		return logger;
	}

	public static Collection<Player> getOnlinePlayers(){
		return server.getOnlinePlayers();
	}

	public static World getWorld(){
		return server.getWorld();
	}

	public static MinecraftServer getMinecraftServer(){
		return server;
	}
	
	public static ServerConfiguration getConfiguration(){
		return configuration;
	}
	
	public static String getHost(){
		return host;
	}
	
	public static int getPort(){
		return port;
	}

}
