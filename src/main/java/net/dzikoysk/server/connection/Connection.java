package net.dzikoysk.server.connection;

import net.dzikoysk.server.UnknownPandaServer;
import net.dzikoysk.server.connection.protocol.PacketRecognizer;
import net.dzikoysk.server.connection.protocol.util.DataSerializer;

import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread {

    private final Socket client;
    private PlayerConnection connection;
    private PacketRecognizer recognizer;
    private DataSerializer serializer;

    public Connection(Socket client) throws IOException {
        this.client = client;
        this.connection = new PlayerConnection(this);
        this.recognizer = new PacketRecognizer(connection);
        this.serializer = connection.getDataSerializer();
        this.setName("UnknownPandaServer - Connection Thread [#" + client.getInetAddress().getHostAddress() + "]");
    }

    @Override
    public void run() {
        while (true) {
            if (isClosed()) {
                return;
            }
            try {
                recognizer.receive(serializer);
            }
            catch (Exception e) {
                e.printStackTrace();
                disconnect(2);
                return;
            }
        }
    }

    public void disconnect(int i) {
        try {
            client.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        connection.disconnect();
        switch (i) {
            case 0:
                UnknownPandaServer.getLogger().info("[" + client.getInetAddress().getHostAddress() + "] Connection closed by client");
                break;
            case 1:
                UnknownPandaServer.getLogger().info("[" + client.getInetAddress().getHostAddress() + "] Connection closed");
                break;
            case 2:
                UnknownPandaServer.getLogger().info("[" + client.getInetAddress().getHostAddress() + "] Packet error");
                break;
            default:
                UnknownPandaServer.getLogger().info("[" + client.getInetAddress().getHostAddress() + "] Connection broken");
                break;
        }
    }

    public boolean isClosed() {
        return this.client.isClosed();
    }

    public boolean isConnected() {
        return this.client.isConnected();
    }

    public DataSerializer getDataSerializer() {
        return this.serializer;
    }

    public PacketRecognizer getRecognizer() {
        return this.recognizer;
    }

    public PlayerConnection getPlayerConnection() {
        return this.connection;
    }

    public final Socket getClient() {
        return this.client;
    }

}