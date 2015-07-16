package net.dzikoysk.server.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.util.UUID;

public class Utils {

	public static UUID getUUID(String s){
		try {
			return UUID.nameUUIDFromBytes(s.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ServerSocket getServerSocket(int port, int con){
		try {
			return new ServerSocket(port, con);
		} catch (IOException e) {
			System.out.println("CANNOT BIND PORT " + Integer.toString(port));
		}
		return null;
	}
	
}
