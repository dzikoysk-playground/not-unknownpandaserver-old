package net.dzikoysk.server.entity;

import net.dzikoysk.server.connection.PlayerConnection;
import net.dzikoysk.server.element.GameMode;
import net.dzikoysk.server.util.Utils;
import net.dzikoysk.server.world.Location;

import java.util.UUID;

public class Player {

    private final PlayerConnection connection;
    private final String name;
    private final UUID uuid;
    private final Human human;
    private String displayName;
    private Location location;
    private GameMode gamemode;
    private boolean ground;
    private int ping;

    public Player(PlayerConnection connection, String name) {
        this.connection = connection;
        this.name = name;
        this.uuid = Utils.getUUID(name);
        this.human = new Human();
        this.gamemode = GameMode.CREATIVE;
        this.displayName = name;
    }

    void setGround(boolean ground) {
        this.ground = ground;
    }

    public boolean isConnected() {
        return this.connection.isConnected();
    }

    public boolean isOnGround() {
        return this.ground;
    }

    public int getPing() {
        return this.ping;
    }

    public GameMode getGamemode() {
        return this.gamemode;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String name) {
        this.displayName = name;
    }

    public PlayerConnection getPlayerConnection() {
        return this.connection;
    }

    public String getName() {
        return this.name;
    }

    public UUID getUUID() {
        return this.uuid;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return uuid.equals(player.uuid);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
