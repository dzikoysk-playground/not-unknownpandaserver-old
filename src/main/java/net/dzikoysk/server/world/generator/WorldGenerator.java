package net.dzikoysk.server.world.generator;

import net.dzikoysk.server.world.ChunkColumn;

public interface WorldGenerator {

    ChunkColumn getNewChunk(int xPos, int zPos);

}
