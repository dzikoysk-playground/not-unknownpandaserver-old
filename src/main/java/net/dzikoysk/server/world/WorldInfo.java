package net.dzikoysk.server.world;

import net.dzikoysk.server.world.generator.WorldType;

public class WorldInfo {

	private WorldType type;
	private String name;
	private Location spawn;

	public WorldInfo(){
		this.spawn = new Location(0, 60, 0);
	}

	public WorldInfo type(WorldType type){
		this.type = type;
		return this;
	}
	
	public WorldInfo name(String name){
		this.name = name;
		return this;
	}

	public Location getSpawn(){
		return this.spawn;
	}

	public String getName(){
		return this.name;
	}
	
	public WorldType getWorldType(){
		return this.type;
	}

}
