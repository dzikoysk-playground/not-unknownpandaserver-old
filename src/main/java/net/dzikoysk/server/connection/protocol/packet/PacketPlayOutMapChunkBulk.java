package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataEncoder;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;
import net.dzikoysk.server.world.ChunkColumn;
import net.dzikoysk.server.world.ChunkData;

public class PacketPlayOutMapChunkBulk extends Packet {

    private final ChunkColumn[] columns;
    private byte[][] bulk;

    public PacketPlayOutMapChunkBulk(ChunkColumn... columns) {
        this.columns = columns;
        this.bulk = new byte[columns.length][];
    }

    @Override
    public void send(DataSerializer data) {
        data.writeBoolean(true); // sky
        DataEncoder.encodeInt(data, columns.length); // count
        for (int i = 0; i < columns.length; i++) {
            ChunkColumn column = columns[i];
            ChunkData c = new ChunkData(column);
            data.writeInt(column.getX());
            data.writeInt(column.getZ());
            data.writeShort(c.getMask());
            bulk[i] = c.getContent();
        }
        for (byte[] part : bulk) {
            data.write(part);
        }
    }

    @Override
    public PacketInfo getPacketInfo() {
        return PacketInfo.PLAY_OUT_MAP_CHUNK_BULK;
    }

}
