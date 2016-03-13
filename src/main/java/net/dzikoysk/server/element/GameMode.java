package net.dzikoysk.server.element;

public enum GameMode {

	SURVIVAL(0x0),
	CREATIVE(0x1),
	ADVENTURE(0x2),
	SPECTATOR(0x3),
	HARDCORE(0x8);

	private final int gamemode;

	private GameMode(int gamemode){
		this.gamemode = gamemode;
	}

	public int getID(){
		return this.gamemode;
	}

}
