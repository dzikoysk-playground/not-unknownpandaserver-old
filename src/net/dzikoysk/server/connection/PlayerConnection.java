package net.dzikoysk.server.connection;

import net.dzikoysk.server.MinecraftServer;
import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketManager;
import net.dzikoysk.server.connection.protocol.WorldProvider;
import net.dzikoysk.server.connection.protocol.util.DataEncoder;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;
import net.dzikoysk.server.entity.Player;
import net.dzikoysk.server.util.io.DataInStream;
import net.dzikoysk.server.util.io.DataOutStream;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PlayerConnection {

	private final Connection connection;
	private DataInputStream input;
	private DataOutputStream output;
	private PacketManager protocol;
	private WorldProvider world;
	private Player player;
	private boolean alive;
	private boolean notcon;
	private boolean ready;
	
	public PlayerConnection(Connection connection) throws IOException {
		this.output = new DataOutStream(connection.getClient().getOutputStream());
		this.input = new DataInStream(connection.getClient().getInputStream());
		this.protocol = new PacketManager(this);
		this.connection = connection;
	}
	
	public void sendPacket(Packet packet) throws IOException {
		if(!isConnected()) return;
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutStream d = new DataOutStream(b);
		d.writeByte(packet.getPacketInfo().getID());
		packet.send(new DataSerializer(this, d));
		DataEncoder.encodeInt(output, b.size());
		output.write(b.toByteArray());
		d.close();
	}

	public void ready(){
		if(ready) return;
		ready = true;
		MinecraftServer.getMinecraftServer().addPlayer(this.player);
	}
	
	public void disconnect(){
		if(player != null) MinecraftServer.getMinecraftServer().removePlayer(player);
		this.notcon = true;
	}

	public void setPlayer(Player player){
		this.player = player;
	}
	
	public void setWorldProvier(WorldProvider provider){
		this.world = provider;
	}

	public boolean isAlive(){
		return this.alive;
	}

	public boolean isConnected(){
		return !this.notcon && connection.isConnected();
	}

	public Connection getConnection(){
		return this.connection;
	}
	
	public DataSerializer getDataSerializer(){
		return new DataSerializer(this, output, input);
	}
	
	public PacketManager getProtocolManager(){
		return this.protocol;
	}
	
	public WorldProvider getWorldProvider(){
		return this.world;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
}
