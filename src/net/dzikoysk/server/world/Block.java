package net.dzikoysk.server.world;

public class Block {

	private final Material material;
	private final Location location;
	private int meta;
	
	public Block(Material material, Location location, int meta){
		this.material = material;
		this.location = location;
		this.meta = meta;
	}
	
	public Block(Material material, Location location){
		this(material, location, 0);
	}
	
	public Location getLocation(){
		return this.location;
	}
	
	public Material getMaterial(){
		return this.material;
	}
	
	public int getMeta(){
		return this.meta;
	}
	
}
