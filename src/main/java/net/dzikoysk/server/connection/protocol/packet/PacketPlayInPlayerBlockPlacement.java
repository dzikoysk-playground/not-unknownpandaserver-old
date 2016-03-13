package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataDecoder;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;
import net.dzikoysk.server.world.BlockFace;
import net.dzikoysk.server.world.Location;
import net.dzikoysk.server.world.Material;

public class PacketPlayInPlayerBlockPlacement extends Packet {

    private Location position;
    private BlockFace face;
    private Material material;
    private byte x, y, z;

    @Override
    public void receive(DataSerializer data) {
        position = DataDecoder.decodeLocation(data);
        face = BlockFace.valueOf(data.readByte());
        material = Material.fromId(data.skipBytes(data.available() - 3));
        x = data.readByte();
        y = data.readByte();
        z = data.readByte();
    }

    @Override
    public PacketInfo getPacketInfo() {
        return PacketInfo.PLAY_IN_PLAYER_BLOCK_PLACEMENT;
    }

    public Location getPosition() {
        return this.position;
    }

    public BlockFace getFace() {
        return this.face;
    }

    public Material getMaterial() {
        return this.material;
    }

    public byte[] getCursorPosition() {
        return new byte[]{x, y, z};
    }

}
