package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataEncoder;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;
import net.dzikoysk.server.world.ChunkColumn;

public class PacketPlayOutMapChunkData extends Packet {

    private final ChunkColumn column;
    private byte[] content;
    private int mask;

    public PacketPlayOutMapChunkData(ChunkColumn column) {
        this.column = column;
        this.content = new byte[0];
        this.mask = 0xFFFF;
    }

    @Override
    public void send(DataSerializer data) {
        data.writeInt(column.getX());
        data.writeInt(column.getZ());
        data.writeBoolean(true);
        data.writeShort(mask);
        DataEncoder.encodeInt(data, content.length);
        data.write(content);
    }

    @Override
    public PacketInfo getPacketInfo() {
        return PacketInfo.PLAY_OUT_CHUNK_DATA;
    }

    public byte[] getContent() {
        return this.content;
    }

    public int getMask() {
        return this.mask;
    }

}
