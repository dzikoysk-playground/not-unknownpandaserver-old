package net.dzikoysk.server.world;

public enum BlockFace {

	DOWN(0),
	UP(1),
	NORTH(2),
	SOUTH(3),
	WEST(4),
	EAST(5);

	private final int face;

	private BlockFace(int i){
		this.face = i;
	}

	public int getFace(){
		return this.face;
	}

	public static BlockFace valueOf(int i){
		for(BlockFace face : values())
			if(face.getFace() == i) return face;
		return null;
	}

}
