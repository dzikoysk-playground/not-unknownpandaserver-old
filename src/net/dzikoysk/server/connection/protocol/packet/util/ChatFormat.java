package net.dzikoysk.server.connection.protocol.packet.util;

import net.dzikoysk.server.element.ChatColor;
import net.dzikoysk.server.util.json.JSONObject;

public class ChatFormat {

	public static String getPlainText(String message){
		JSONObject object = new JSONObject();
		String colorChar = String.valueOf(ChatColor.COLOR_CHAR);
		if(message.contains(colorChar)) {
			for(String s : message.split(colorChar)) {
				if(s.isEmpty()) continue;
				char c = s.charAt(0);
				ChatColor color = ChatColor.getByChar(c);
				object.put("color", color.toString());
				object.put("text", s.substring(1));
			}
		} else object.put("text", message);
		return object.toString();
	}

}
