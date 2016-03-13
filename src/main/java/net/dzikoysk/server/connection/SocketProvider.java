package net.dzikoysk.server.connection;

import net.dzikoysk.server.UnknownPandaServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class SocketProvider extends Thread {

	private final ServerSocket server;
	private final HashMap<InetAddress, Socket> sockets;
	
	public SocketProvider(ServerSocket server){
		this.server = server;
		this.sockets = new HashMap<>(UnknownPandaServer.getConfiguration().getInteger("players") + 20);
		this.setName("UnknownPandaServer - SocketProvider");
	}

	@Override
	public void run(){
		while(true) try {
			Socket client = server.accept();
			if(sockets.containsKey(client.getInetAddress())) {
				Socket socket = sockets.get(client.getInetAddress());
				socket.close();
			}
			sockets.put(client.getInetAddress(), client);
			client.setSoTimeout(7000);
			//UnknownPandaServer.getLogger().info("new Client: " + client.getInetAddress().getHostAddress());
			new Connection(client).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getConnectionsCount(){
		return sockets.size();
	}
	
}
