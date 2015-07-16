package net.dzikoysk.server.connection.protocol.util;

import net.dzikoysk.server.world.Location;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

public class DataEncoder {
	
	public static void encodeLocation(DataSerializer data, Location loc){
		data.writeLong(
			(loc.getBlockX() & 0x3FFFFFF) << 38 | 
			(loc.getBlockY() & 0xFFF) << 26 | 
			loc.getBlockZ() & 0x3FFFFFF
		);
	}

	public static void encodeUUID(DataSerializer data, UUID uuid){
		data.writeLong(uuid.getMostSignificantBits());
		data.writeLong(uuid.getLeastSignificantBits());
	}
	
	public static void encodeString(DataSerializer data, String s){
		encodeInt(data, s.length());
		data.writeBytes(s);
	}
	
	public static void encodeInt(DataSerializer data, int i) {
		try {
			encodeInt(data.getDataOutStream(), i);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void encodeInt(DataOutputStream data, int i) throws IOException {
		while (true) {
			if ((i & 0xFFFFFF80) == 0) {
			  data.writeByte(i);
			  return;
			}
			data.writeByte(i & 0x7F | 0x80);
			i >>>= 7;
		}
	}

}
