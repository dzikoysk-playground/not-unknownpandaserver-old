package net.dzikoysk.server.connection.protocol.util;

import net.dzikoysk.server.world.Location;

import java.io.DataInputStream;
import java.io.IOException;

public class DataDecoder {

	public static Location decodeLocation(DataSerializer data){
		long val = data.readLong();
		return new Location(val >> 38, (val >> 26) & 0xFFF, val << 38 >> 38);
	}
	
	public static String decodeString(DataSerializer data){
		int length = data.read();
		char[] chars = new char[length];
		for(int i = 0; i < length; i++){
			char c = (char) data.read();
			chars[i] = c;
		}
		return new String(chars);
	}
	
	public static int decodeInt(DataSerializer data){
		try {
			return decodeInt(data.getDataInStream());
		} catch (Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	public static int decodeInt(DataInputStream data) throws IOException {
        return decodeInt(data.readByte());
	}

	public static int decodeInt(int k) {
		int i = 0;
		int j = 0;
		while (true) {
			i |= (k & 0x7F) << j++ * 7;
			if (j > 5) throw new RuntimeException("VarInt too big");
			if ((k & 0x80) != 128) break;
		}
		return i;
	}

}
