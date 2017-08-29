package net.dzikoysk.server.element;

public enum GameMode {

    SURVIVAL(0),
    CREATIVE(1),
    ADVENTURE(2),
    SPECTATOR(3),
    HARDCORE(8);

    private final int gamemode;

    GameMode(int gamemode) {
        this.gamemode = gamemode;
    }

    public int getID() {
        return this.gamemode;
    }

}
