package net.dzikoysk.server.connection.protocol;

import net.dzikoysk.server.connection.protocol.packet.*;

public enum PacketInfo {

	HANDSHAKING_IN_SET_PROTOCOL(0x00, ProtocolState.HANDSHAKING, ProtocolBound.SERVER, PacketHandshakingInSetProtocol.class),
	
	STATUS_IO_PING(0x01, ProtocolState.STATUS, ProtocolBound.CLIENT_SERVER, PacketStatusIOPing.class),
	STATUS_IN_REQUEST(0x00, ProtocolState.STATUS, ProtocolBound.SERVER, PacketStatusInRequest.class),
	STATUS_OUT_SERVER_INFO(0x00, ProtocolState.STATUS, ProtocolBound.CLIENT, PacketStatusOutServerInfo.class),
	
	LOGIN_IN_START(0x00, ProtocolState.LOGIN, ProtocolBound.SERVER, PacketLoginInStart.class),
	LOGIN_IN_ENCRYPTION_RESPONSE(0x01, ProtocolState.LOGIN, ProtocolBound.SERVER, PacketLoginInEncryptionResponse.class),
	LOGIN_OUT_SUCCESS(0x02, ProtocolState.LOGIN, ProtocolBound.CLIENT, PacketLoginOutSuccess.class),
	LOGIN_OUT_ENCRYPTION_REQUEST(0x01, ProtocolState.LOGIN, ProtocolBound.CLIENT, PacketLoginOutEncryptionRequest.class),
	
	PLAY_IO_KEEP_ALIVE(0x00, ProtocolState.PLAY, ProtocolBound.CLIENT_SERVER, PacketPlayIOKeepAlive.class),

	PLAY_IN_CHAT_MESSAGE(0x01, ProtocolState.PLAY, ProtocolBound.SERVER, PacketPlayInChatMessage.class),
	PLAY_IN_USE_ENTITY(0x02, ProtocolState.PLAY, ProtocolBound.SERVER, PacketPlayInUseEntity.class),
	PLAY_IN_PLAYER(0x03, ProtocolState.PLAY, ProtocolBound.SERVER, PacketPlayInPlayer.class),
	PLAY_IN_PLAYER_POSITION(0x04, ProtocolState.PLAY, ProtocolBound.SERVER, PacketPlayInPlayerPosition.class),
	PLAY_IN_PLAYER_LOOK(0x05, ProtocolState.PLAY, ProtocolBound.SERVER, PacketPlayInPlayerLook.class),
	PLAY_IN_PLAYER_POSITION_AND_LOOK(0x06, ProtocolState.PLAY, ProtocolBound.SERVER, PacketPlayInPlayerPositionAndLook.class),
	PLAY_IN_PLAYER_DIGGING(0x07, ProtocolState.PLAY, ProtocolBound.SERVER, PacketPlayInPlayerDigging.class),
	PLAY_IN_PLAYER_BLOCK_PLACEMENT(0x08, ProtocolState.PLAY, ProtocolBound.SERVER, PacketPlayInPlayerBlockPlacement.class),
	PLAY_IN_ANIMATION(0x0A, ProtocolState.PLAY, ProtocolBound.SERVER, PacketPlayInAnimation.class),
	PLAY_IN_ENTITY_ACTION(0x0B, ProtocolState.PLAY, ProtocolBound.SERVER, PacketPlayInEntityAction.class),
	PLAY_IN_CLIENT_SETTINGS(0x15, ProtocolState.PLAY, ProtocolBound.SERVER, PacketPlayInClientSettings.class),
	PLAY_IN_PLUGIN_MESSAGE(0x17, ProtocolState.PLAY, ProtocolBound.SERVER, PacketPlayInPluginMessage.class),
	
	PLAY_OUT_JOIN(0x01, ProtocolState.PLAY, ProtocolBound.CLIENT, PacketPlayOutJoin.class),
	PLAY_OUT_CHAT_MESSAGE(0x02, ProtocolState.PLAY, ProtocolBound.CLIENT, PacketPlayOutChatMessage.class),
	PLAY_OUT_TIME_UPDATE(0x03, ProtocolState.PLAY, ProtocolBound.CLIENT, PacketPlayOutTimeUpdate.class),
	PLAY_OUT_SPAWN_POSITION(0x05, ProtocolState.PLAY, ProtocolBound.CLIENT, PacketPlayOutSpawnPosition.class),
	PLAY_OUT_PLAYER_POSITION_AND_LOOK(0x08, ProtocolState.PLAY, ProtocolBound.CLIENT, PacketPlayOutPlayerPositionAndLook.class),
	PLAY_OUT_CHUNK_DATA(0x21, ProtocolState.PLAY, ProtocolBound.CLIENT, PacketPlayOutMapChunkData.class),
	PLAY_OUT_MULTI_BLOCK_CHANGE(0x22, ProtocolState.PLAY, ProtocolBound.CLIENT, PacketPlayOutMultiBlockChange.class),
	PLAY_OUT_MAP_CHUNK_BULK(0x26, ProtocolState.PLAY, ProtocolBound.CLIENT, PacketPlayOutMapChunkBulk.class),
	PLAY_OUT_PLAYER_LIST_ITEM(0x38, ProtocolState.PLAY, ProtocolBound.CLIENT, PacketPlayOutPlayerListItem.class),
	PLAY_OUT_PLAYER_ABILITIES(0x39, ProtocolState.PLAY, ProtocolBound.CLIENT, PacketPlayOutPlayerAbilities.class);
	
	private final int id;
	private final ProtocolState state;
	private final ProtocolBound bound;
	private final Class<? extends Packet> clazz;
	
	private PacketInfo(int id, ProtocolState state, ProtocolBound bound, Class<? extends Packet> clazz){
		this.id = id;
		this.state = state;
		this.bound = bound;
		this.clazz = clazz;
	}
	
	public int getID(){
		return this.id;
	}
	
	public ProtocolState getState(){
		return this.state;
	}
	
	public ProtocolBound getBound(){
		return this.bound;
	}
	
	public Class<? extends Packet> getPacketClass(){
		return this.clazz;
	}
	
	public Packet getPacket() throws Exception {
		return this.clazz.newInstance();
	}

	public static PacketInfo getPacket(int id, ProtocolState state, ProtocolBound bound){
		for(PacketInfo i : PacketInfo.values())
			if(i.getID() == id && i.getState() == state && 
			  (i.getBound() == bound || i.getBound() == ProtocolBound.CLIENT_SERVER))
				return i;
		return null;
	}

}
