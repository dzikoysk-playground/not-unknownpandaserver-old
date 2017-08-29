package net.dzikoysk.server.connection.protocol.packet.util;

public enum DiggingStatus {

    STARTED(0),
    CANCELLED(1),
    FINISHED(2),
    DROP_ITEM_STACK(3),
    DROP_ITEM(4),
    SHOOT_ARROW(5);

    private final int status;

    DiggingStatus(int status) {
        this.status = status;
    }

    public static DiggingStatus valueOf(byte b) {
        for (DiggingStatus status : values()) {
            if (status.getStatus() == b) {
                return status;
            }
        }
        return CANCELLED;
    }

    public int getStatus() {
        return this.status;
    }

}
