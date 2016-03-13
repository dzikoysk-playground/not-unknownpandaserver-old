package net.dzikoysk.server.entity;

public enum EntityAction {

    CROUCH(0),
    UNCROUCH(1),
    LEAVE_BED(2),
    START_SPRINTING(3),
    STOP_SPRINTING(4),
    JUMP_WITH_HORSE(5),
    OPEN_INVENTORY(6);

    private final int id;

    private EntityAction(int id) {
        this.id = id;
    }

    public static EntityAction getAction(int id) {
        for (EntityAction pi : values()) {
            if (pi.getID() == id) {
                return pi;
            }
        }
        return null;
    }

    public int getID() {
        return this.id;
    }

}
