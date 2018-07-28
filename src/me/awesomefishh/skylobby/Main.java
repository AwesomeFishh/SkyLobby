package me.awesomefishh.skylobby;

import me.awesomefishh.skylobby.pluginmessages.PlMessageListener;
import me.awesomefishh.skylobby.pluginmessages.PlMessageMethods;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {

    private static Main instance;
    private PlMessageMethods messageMethods;

    private String serverName;
    private Map<String, String> gameStates = new HashMap<>();

    public void onEnable() {
        registerClasses();
        registerListeners();
    }

    private void registerClasses() {
        this.messageMethods = new PlMessageMethods();
    }

    private void registerListeners() {
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new PlMessageListener());
    }


    public Main() {
        instance = this;
    }

    public static Main getInstance() {
        return instance;
    }

    public PlMessageMethods getMessageMethods() {
        return messageMethods;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Map<String, String> getGameStates() {
        return gameStates;
    }
}
