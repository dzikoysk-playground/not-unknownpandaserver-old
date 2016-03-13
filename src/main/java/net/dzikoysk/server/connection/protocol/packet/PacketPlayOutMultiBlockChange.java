package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataEncoder;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;
import net.dzikoysk.server.world.Block;
import net.dzikoysk.server.world.Chunk;
import net.dzikoysk.server.world.ChunkColumn;
import net.dzikoysk.server.world.Location;

import java.nio.ByteBuffer;

public class PacketPlayOutMultiBlockChange extends Packet {

    private final ChunkColumn chunk;

    public PacketPlayOutMultiBlockChange(ChunkColumn chunk) {
        this.chunk = chunk;
    }

    @Override
    public void send(DataSerializer data) {
        data.writeInt(chunk.getX());
        data.writeInt(chunk.getZ());
        int length = 16 * 16 * 16 * 16 * 4;
        byte[] bs = new byte[length];
        int i = 0;
        for (Chunk chunk : this.chunk.getChunks()) {
            for (Block block : chunk.getBlocks()) {
                Location loc = block.getLocation();
                byte[] c = ByteBuffer.allocate(4).putInt(
                        loc.getBlockX() << 28 |
                                loc.getBlockZ() << 24 |
                                loc.getBlockY() << 16 |
                                block.getMaterial().getId() << 4 |
                                0
                ).array();
                bs[i * 4] = c[0];
                bs[i * 4 + 1] = c[1];
                bs[i * 4 + 2] = c[2];
                bs[i * 4 + 3] = c[3];
                i++;
            }
        }
        DataEncoder.encodeInt(data, i);
        data.write(bs);
    }

    @Override
    public PacketInfo getPacketInfo() {
        return PacketInfo.PLAY_OUT_MULTI_BLOCK_CHANGE;
    }

}
