package net.dzikoysk.server.world;

public class Location {

    private double x, y, z;
    private float yaw, pitch;

    public Location(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Location(double x, double y, double z, float yaw, float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public boolean isOnGround() {
        return true;
    }

    public Look getLook() {
        return new Look(yaw, pitch);
    }

    public int getRelative() {
        return (int) (x * y * z);
    }

    public int getBlockX() {
        return (int) this.x;
    }

    public int getBlockY() {
        return (int) this.y;
    }

    public int getBlockZ() {
        return (int) this.z;
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

}
