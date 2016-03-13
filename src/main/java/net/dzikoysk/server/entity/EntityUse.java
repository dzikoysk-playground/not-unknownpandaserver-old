package net.dzikoysk.server.entity;

public enum EntityUse {

    INTERACT(0),
    ATTACK(1),
    INTERACT_AT(2);

    private final int id;

    private EntityUse(int id) {
        this.id = id;
    }

    public static EntityUse getType(int id) {
        for (EntityUse pi : values()) {
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
