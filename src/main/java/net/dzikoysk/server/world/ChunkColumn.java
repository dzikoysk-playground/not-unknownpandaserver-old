package net.dzikoysk.server.world;

public class ChunkColumn {

    private final int x;
    private final int z;
    private Chunk[] chunks;

    public ChunkColumn(int x, int z) {
        this.chunks = new Chunk[16];
        this.x = x;
        this.z = z;
    }

    public void setChunk(Chunk chunk) {
        this.chunks[chunk.getY()] = chunk;
    }

    public int getX() {
        return this.x;
    }

    public int getZ() {
        return this.z;
    }

    public Chunk[] getChunks() {
        return this.chunks;
    }
}
