package net.dzikoysk.server.world;

public class ChunkData {

	private byte[] content;
	private int mask;
	
	public ChunkData(ChunkColumn column){
		calc(column);
	}
	
	private void calc(ChunkColumn column){
		int p = 0;
		for(int i = 0; i < 16; i++){
			Chunk chunk = column.getChunks()[i];
			if(chunk.getSolidCount() == 0) continue;
			this.mask |= 1 << i;
		}
		
		int f = 0;
		for(int i = 0; i < 16; i++)
			if((this.mask & 1 << i ) >> i == 1) f++;
		this.content = new byte[f*4096*3 + 256];
		
		for(int i = 0; i < 16; i++){
			if((this.mask & 1 << i ) >> i == 0) continue;
			Chunk chunk = column.getChunks()[i];
			for(int b = 0; b < 4096; b++){
				Block block = chunk.getBlocks()[b];
				this.content[p] = (byte) ((block.getMaterial().getId() << 4) | (block.getMeta() & 15));
				this.content[p + 1] = 1 >> 5;
				p += 2;
			}
		}
	}
	
	public byte[] getContent(){
		return this.content;
	}
	
	public int getMask(){
		return this.mask;
	}
	
}
