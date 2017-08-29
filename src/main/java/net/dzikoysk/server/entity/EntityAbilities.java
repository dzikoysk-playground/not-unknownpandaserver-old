package net.dzikoysk.server.entity;

public enum EntityAbilities {

    CREATIVE_MODE(1),
    FLYING(2),
    FLY(4),
    GOD_MODE(8);

    private final int flag;

    EntityAbilities(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return this.flag;
    }

}
