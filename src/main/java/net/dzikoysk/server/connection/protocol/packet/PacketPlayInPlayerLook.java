package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;
import net.dzikoysk.server.world.Look;

public class PacketPlayInPlayerLook extends Packet {

    private float yaw, pitch;
    private boolean onGround;

    @Override
    public void receive(DataSerializer data) {
        yaw = data.readFloat();
        pitch = data.readFloat();
        onGround = data.readBoolean();
    }

    @Override
    public PacketInfo getPacketInfo() {
        return PacketInfo.PLAY_IN_PLAYER_LOOK;
    }

    public Look getLook() {
        return new Look(yaw, pitch);
    }

    public boolean inOnGround() {
        return onGround;
    }

}
