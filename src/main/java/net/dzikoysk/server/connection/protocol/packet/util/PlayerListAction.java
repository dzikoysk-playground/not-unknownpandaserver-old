package net.dzikoysk.server.connection.protocol.packet.util;

public enum PlayerListAction {

    ADD_PLAYER(0),
    UPDATE_GAMEMODE(1),
    UPDATE_PING(2),
    UPDATE_DISPLAY_NAME(3),
    REMOVE_PLAYER(4);

    private final int id;

    PlayerListAction(int i) {
        this.id = i;
    }

    public int getID() {
        return this.id;
    }

}
