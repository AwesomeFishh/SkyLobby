package me.awesomefishh.skylobby;

import me.awesomefishh.skylobby.commands.CommandManager;
import me.awesomefishh.skylobby.configurations.ConfigManager;
import me.awesomefishh.skylobby.database.DatabaseManager;
import me.awesomefishh.skylobby.game.GameData;
import me.awesomefishh.skylobby.listeners.JoinListener;
import me.awesomefishh.skylobby.schedulers.UpdateData;
import me.awesomefishh.skylobby.schedulers.UpdateSigns;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends JavaPlugin {

    private static Main instance;
    private ConfigManager configManager;
    private DatabaseManager databaseManager;
    private CommandManager commandManager;
    private UpdateSigns updateSigns;
    private UpdateData updateData;

    private String prefix;

    private String serverName;
    private Map<String, GameData> gameData = new HashMap<>();

    public void onEnable() {
        saveDefaultConfig();

        registerClasses();
        registerListeners();
    }

    private void registerClasses() {
        this.prefix = ChatColor.translateAlternateColorCodes('&', getConfig().getString("chat-prefix"));

        this.configManager = new ConfigManager();
        this.configManager.setupConfigs();
        this.databaseManager = new DatabaseManager();
        this.databaseManager.establishConnection();
        this.commandManager = new CommandManager();
        this.commandManager.setup();
        this.updateData = new UpdateData();
        this.updateData.initiate();
        this.updateSigns = new UpdateSigns();
        this.updateSigns.initiate();
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
    }


    public Main() {
        instance = this;
    }

    public static Main getInstance() {
        return instance;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public UpdateSigns getUpdateSigns() {
        return updateSigns;
    }

    public UpdateData getUpdateData() {
        return updateData;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public Map<String, GameData> getGameData() {
        return gameData;
    }
}
