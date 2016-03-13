package net.dzikoysk.server.world;

import net.dzikoysk.server.world.generator.WorldGenerator;
import net.dzikoysk.server.world.generator.WorldType;
import net.dzikoysk.server.world.generator.flat.FlatGenerator;

import java.util.HashMap;
import java.util.Map;

public class World {

	private final WorldInfo specyfication;
	private Map<Long, ChunkColumn> chunks;
	private WorldGenerator gen;
	private Location spawn;
	
	public World(WorldInfo wi){
		chunks = new HashMap<>();
		specyfication = wi;
		spawn = wi.getSpawn();
		if(specyfication.getWorldType() == WorldType.FLAT)
			gen = new FlatGenerator(this);
	}

	public void loadSpawnTerrain(int distance){
		for(int x = spawn.getBlockX() - distance; x < spawn.getBlockX() + distance; x++)
			for(int z = spawn.getBlockZ() - distance; z < spawn.getBlockZ() + distance; z++)
				getChunkColumn(x, z);
	}
	
	public ChunkColumn getChunkColumn(int x, int z){
		long l = (long) x << 32 | z & 0xFFFFFFFFL;
		ChunkColumn column = chunks.get(l);
		if(column == null){
			column = gen.getNewChunk(x, z);
			chunks.put(l, column);
		}
		return column;
	}
	
	public Map<Long, ChunkColumn> getChunks(){
		return this.chunks;
	}

	public Location getSpawn(){
		return this.spawn;
	}

	public WorldInfo getSpecyfication() {
		return this.specyfication;
	}

}
