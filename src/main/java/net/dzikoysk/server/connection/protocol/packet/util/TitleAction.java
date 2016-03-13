package net.dzikoysk.server.connection.protocol.packet.util;

public enum TitleAction {

    SET_TITLE(0),
    SET_SUBTITLE(1),
    SET_TIMES_AND_DISPLAY(2),
    HIDE(3),
    RESET(4);

    private final int id;

    private TitleAction(int id) {
        this.id = id;
    }

    public int getID() {
        return this.id;
    }

}
