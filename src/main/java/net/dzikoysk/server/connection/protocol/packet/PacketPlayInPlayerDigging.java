package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.packet.util.DiggingStatus;
import net.dzikoysk.server.connection.protocol.util.DataDecoder;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;
import net.dzikoysk.server.world.BlockFace;
import net.dzikoysk.server.world.Location;

public class PacketPlayInPlayerDigging extends Packet {

	private DiggingStatus status;
	private Location position;
	private BlockFace face;

	@Override
	public void receive(DataSerializer data){
		status = DiggingStatus.valueOf(data.readByte());
		position = DataDecoder.decodeLocation(data);
		face = BlockFace.valueOf(data.readByte());
	}

    @Override
    public PacketInfo getPacketInfo() {
	    return PacketInfo.PLAY_IN_PLAYER_DIGGING;
    }

	public DiggingStatus getStatus(){
		return this.status;
	}

	public Location getPosition(){
		return this.position;
	}

	public BlockFace getFace(){
		return this.face;
	}

}
