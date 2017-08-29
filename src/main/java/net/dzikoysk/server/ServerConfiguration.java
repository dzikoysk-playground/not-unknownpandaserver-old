package net.dzikoysk.server;

import org.panda_lang.panda.util.configuration.PandaConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class ServerConfiguration {

    private PandaConfiguration configuration;

    public ServerConfiguration() {
        load();
    }

    private void load() {
        try {
            File file = new File("server.pc");
            if (!file.exists()) {
                loadDefault(file);
                return;
            }
            configuration = new PandaConfiguration(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Comment
    private void loadDefault(File file) throws IOException {
        file.createNewFile();
        PrintWriter writer = new PrintWriter(file);
        writer.println("# UnknownPandaServer Configuration");
        writer.println("name: UnknownPandaServer");
        writer.println("host: 127.0.0.1");
        writer.println("port: 25565");
        writer.println("protocol: 47");
        writer.println("players: 60");
        writer.println("world-name: world");
        writer.println("motd: UnknownPandaServer 1.8.x #P47_0.1.3");
        writer.close();
        configuration = new PandaConfiguration(file);
    }

    public String getString(String key) {
        return configuration.getString(key);
    }

    public int getInteger(String key) {
        return configuration.getInt(key);
    }

}
