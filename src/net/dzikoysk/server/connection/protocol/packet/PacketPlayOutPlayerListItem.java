package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.UnknownPandaServer;
import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.packet.util.ChatFormat;
import net.dzikoysk.server.connection.protocol.packet.util.PlayerListAction;
import net.dzikoysk.server.connection.protocol.packet.util.PlayerListType;
import net.dzikoysk.server.connection.protocol.util.DataEncoder;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;
import net.dzikoysk.server.entity.Player;

public class PacketPlayOutPlayerListItem extends Packet {

	private final PlayerListType type;
	private final PlayerListAction action;

	public PacketPlayOutPlayerListItem(PlayerListType type, PlayerListAction action){
		this.type = type;
		this.action = action;
	}

	@Override
	public void send(DataSerializer data){
		DataEncoder.encodeInt(data, action.getID());
		DataEncoder.encodeInt(data, UnknownPandaServer.getOnlinePlayers().size());
		for(Player player : UnknownPandaServer.getOnlinePlayers()){
			DataEncoder.encodeUUID(data, player.getUUID());
			switch (action){
				case ADD_PLAYER:
					DataEncoder.encodeString(data, player.getName());
					DataEncoder.encodeInt(data, 0);
					DataEncoder.encodeInt(data, player.getGamemode().getID());
					DataEncoder.encodeInt(data, player.getPing());
					data.writeBoolean(true);
					DataEncoder.encodeString(data, ChatFormat.getPlainText(player.getDisplayName()));
					break;
				case UPDATE_GAMEMODE:
					DataEncoder.encodeInt(data, player.getGamemode().getID());
					break;
				case UPDATE_PING:
					DataEncoder.encodeInt(data, player.getPing());
					break;
				case UPDATE_DISPLAY_NAME:
					data.writeBoolean(true);
					DataEncoder.encodeString(data, ChatFormat.getPlainText(player.getDisplayName()));
					break;
				case REMOVE_PLAYER:
					break;
			}
		}
	}

	@Override
	public PacketInfo getPacketInfo(){
		return PacketInfo.PLAY_OUT_PLAYER_LIST_ITEM;
	}

	public PlayerListAction getAction() {
		return this.action;
	}

	public PlayerListType getType() {
		return this.type;
	}

}
