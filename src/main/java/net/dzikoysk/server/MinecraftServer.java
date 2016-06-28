package net.dzikoysk.server;

import net.dzikoysk.server.connection.SocketProvider;
import net.dzikoysk.server.connection.protocol.packet.PacketPlayOutPlayerListItem;
import net.dzikoysk.server.connection.protocol.packet.util.PacketUtil;
import net.dzikoysk.server.connection.protocol.packet.util.PlayerListAction;
import net.dzikoysk.server.connection.protocol.packet.util.PlayerListType;
import net.dzikoysk.server.entity.Player;
import net.dzikoysk.server.util.Utils;
import net.dzikoysk.server.world.World;
import net.dzikoysk.server.world.WorldInfo;
import net.dzikoysk.server.world.generator.WorldType;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collection;

public class MinecraftServer {

    private static MinecraftServer instance;
    private ServerSocket server;
    private SocketProvider socketThread;
    private KeepAliveThread holderThread;
    private ModuleManager moduleManager;
    private Collection<Player> players;
    private World overworld;

    public MinecraftServer(int port) {
        instance = this;
        players = new ArrayList<>();

        server = Utils.getServerSocket(port, 0);
        if (server == null) {
            System.exit(1);
        }

        holderThread = new KeepAliveThread();
        holderThread.start();

        UnknownPandaServer.getLogger().info("Generating world...");
        overworld = new World(new WorldInfo()
                .type(WorldType.FLAT)
                .name("world"));
        overworld.loadSpawnTerrain(11);
        UnknownPandaServer.getLogger().info(overworld.getSpecyfication().getWorldType().toString() + " world '" + overworld.getSpecyfication().getName() + "' generated!");

        socketThread = new SocketProvider(server);
        socketThread.start();

        moduleManager = new ModuleManager();
        moduleManager.create();
        moduleManager.enable();
    }

    public static MinecraftServer getMinecraftServer() {
        return instance;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
        PacketUtil.distributeAll(new PacketPlayOutPlayerListItem(PlayerListType.DEFAULT, PlayerListAction.ADD_PLAYER));
        UnknownPandaServer.broadcastColoredMessage("&e" + player.getName() + " joined to the server!");
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
        UnknownPandaServer.broadcastColoredMessage("&e" + player.getName() + " left the game");
    }

    public World getWorld() {
        return this.overworld;
    }

    public Collection<Player> getOnlinePlayers() {
        return instance.players;
    }

}
