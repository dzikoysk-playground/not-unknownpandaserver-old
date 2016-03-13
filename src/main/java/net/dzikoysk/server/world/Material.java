package net.dzikoysk.server.world;

public enum Material {
	
	AIR(0),
	STONE(1),
	GRASS(2),
	DIRT(3),
	COBBLESTONE(4),
	WOOD(5),
	SAPLING(6),
	BEDROCK(7);

	private final int id;
	
	private Material(int id){
		this.id = id;
	}
	
	public int getId(){
		return this.id;
	}
	
	public static Material fromId(int id){
		for(Material material : values())
			if(id == material.getId()) 
				return material;
		return null;
	}

}
