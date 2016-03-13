package net.dzikoysk.server.world.generator.flat;

import net.dzikoysk.server.world.*;
import net.dzikoysk.server.world.generator.WorldGenerator;

public class FlatGenerator implements WorldGenerator {

    private final World world;

    public FlatGenerator(World world) {
        this.world = world;
    }

    @Override
    public ChunkColumn getNewChunk(int xPos, int zPos) {
        ChunkColumn column = new ChunkColumn(xPos, zPos);
        for (int c = 0; c < 16; c++) {
            Chunk chunk = new Chunk(column, c);
            column.setChunk(chunk);
            if (c > 0) {
                continue;
            }
            for (int y = 0; y < 16; y++) {
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        if (y == 0) {
                            chunk.addBlock(new Block(Material.BEDROCK, new Location(x, y, z)));
                        }
                        else if (y == 15) {
                            chunk.addBlock(new Block(Material.GRASS, new Location(x, y, z)));
                        }
                        else {
                            chunk.addBlock(new Block(Material.DIRT, new Location(x, y, z)));
                        }
                    }
                }
            }
        }
        return column;
    }

    public World getWorld() {
        return this.world;
    }
}
