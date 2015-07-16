package net.dzikoysk.server.connection.protocol;

import net.dzikoysk.server.connection.protocol.packet.PacketPlayOutMapChunkBulk;
import net.dzikoysk.server.entity.Player;
import net.dzikoysk.server.world.ChunkColumn;
import net.dzikoysk.server.world.Location;
import net.dzikoysk.server.world.World;

import java.io.IOException;
import java.util.Stack;

public class WorldProvider {

	private static final int VIEW_DISTANCE = 8;
	private static final int BULK_SIZE = 80;
	private final Player player;
	private final World world;
	
	public WorldProvider(Player player, World world) throws IOException {
		this.player = player;
		this.world = world;
		this.bulk(player.getLocation());
	}
	
	private void bulk(Location loc) throws IOException {
		int locX = loc.getBlockX();
		int locZ = loc.getBlockZ();
		Stack<ChunkColumn> ccs = new Stack<>();
		for(int x = locX - VIEW_DISTANCE; x < locX + VIEW_DISTANCE; x++)
			for(int z = locZ - VIEW_DISTANCE; z < locZ + VIEW_DISTANCE; z++)
				ccs.push(world.getChunkColumn(x, z));

		int times = (int) Math.ceil(ccs.size() / BULK_SIZE);
		for(int t = 0; t < times; t++) {
			ChunkColumn[] cca = ccs.size() > BULK_SIZE ? new ChunkColumn[BULK_SIZE] : new ChunkColumn[ccs.size()];
			for (int i = 0; i < cca.length; i++) cca[i] = ccs.pop();
			player.getPlayerConnection().sendPacket(new PacketPlayOutMapChunkBulk(cca));
		}
	}

	public Player getPlayer(){
		return  this.player;
	}
	
	public World getWorld(){
		return this.world;
	}
	
}