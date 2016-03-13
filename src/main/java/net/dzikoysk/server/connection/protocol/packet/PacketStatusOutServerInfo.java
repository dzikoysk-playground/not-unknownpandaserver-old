package net.dzikoysk.server.connection.protocol.packet;

import net.dzikoysk.server.ServerConfiguration;
import net.dzikoysk.server.UnknownPandaServer;
import net.dzikoysk.server.connection.protocol.Packet;
import net.dzikoysk.server.connection.protocol.PacketInfo;
import net.dzikoysk.server.connection.protocol.util.DataEncoder;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;
import net.dzikoysk.server.entity.Player;
import net.dzikoysk.server.util.json.JSONArray;
import net.dzikoysk.server.util.json.JSONObject;

public class PacketStatusOutServerInfo extends Packet {

    @Override
    public void send(DataSerializer data) {
        String json = getServerInfo().toString();
        DataEncoder.encodeString(data, json);
    }

    @Override
    public PacketInfo getPacketInfo() {
        return PacketInfo.STATUS_OUT_SERVER_INFO;
    }

    private JSONObject getServerInfo() {
        ServerConfiguration configuration = UnknownPandaServer.getConfiguration();
        JSONObject object = new JSONObject();

        JSONObject version = new JSONObject();
        version.put("name", configuration.getString("name"));
        version.put("protocol", configuration.getInteger("protocol"));
        object.put("version", version);

        JSONObject players = new JSONObject();
        players.put("max", configuration.getInteger("players"));
        players.put("online", UnknownPandaServer.getOnlinePlayers().size());

        JSONArray sample = new JSONArray();
        for (Player player : UnknownPandaServer.getOnlinePlayers()) {
            JSONObject element = new JSONObject();
            element.put("name", player.getName());
            element.put("id", player.getUUID().toString());
            sample.put(element);
        }
        players.put("sample", sample);
        object.put("players", players);

        JSONObject desc = new JSONObject();
        desc.put("text", configuration.getString("motd"));
        object.put("description", desc);

        object.put("favicon", "");

        return object;
    }

}
