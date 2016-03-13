package net.dzikoysk.server.connection.protocol;

public enum ProtocolState {

    HANDSHAKING(0),
    STATUS(1),
    LOGIN(2),
    PLAY(3);

    private final int id;

    private ProtocolState(int i) {
        this.id = i;
    }

    public static ProtocolState getProtocolState(int i) {
        return ProtocolState.values()[i];
    }

    public int getID() {
        return this.id;
    }

}
