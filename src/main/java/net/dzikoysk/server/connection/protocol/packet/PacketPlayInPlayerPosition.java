package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;
import net.dzikoysk.server.world.Location;

public class PacketPlayInPlayerPosition extends Packet {

    private double x, y, z;
    private boolean ground;

    @Override
    public void receive(DataSerializer data) {
        x = data.readDouble();
        y = data.readDouble();
        z = data.readDouble();
        ground = data.readBoolean();
    }

    @Override
    public PacketInfo getPacketInfo() {
        return PacketInfo.PLAY_IN_PLAYER_POSITION;
    }

    public Location getLocation() {
        return new Location(x, y, z);
    }

    public boolean isOnGround() {
        return ground;
    }

}
