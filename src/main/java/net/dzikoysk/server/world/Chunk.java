package net.dzikoysk.server.world;


public class Chunk {

    private final ChunkColumn column;
    private final Block[] blocks;
    private final int y;
    private int b;
    private int s;

    public Chunk(ChunkColumn column, int y) {
        this.blocks = new Block[4096];
        this.column = column;
        this.y = y;
    }

    public void addBlock(Block block) {
        if (block == null) {
            return;
        }
        blocks[b] = block;
        b++;
        if (block.getMaterial() != Material.AIR) {
            s++;
        }
    }

    public ChunkColumn getChunkColumn() {
        return this.column;
    }

    public Block[] getBlocks() {
        return this.blocks;
    }

    public int getBlockCount() {
        return this.b;
    }

    public int getSolidCount() {
        return this.s;
    }

    public int getX() {
        return this.column.getX();
    }

    public int getY() {
        return this.y;
    }

    public int getZ() {
        return this.column.getZ();
    }

}
