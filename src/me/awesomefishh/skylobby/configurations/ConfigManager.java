package me.awesomefishh.skylobby.configurations;

import org.bukkit.configuration.Configuration;

public class ConfigManager {

    private KitConfig kitConfig;

    public ConfigManager() {
        this.kitConfig = new KitConfig();
    }

    public void setupConfigs() {

        kitConfig.createConfig();
        kitConfig.saveDefaultConfig();
    }


    public Configuration getKitConfig() {
        return kitConfig.getConfig();
    }

    public void saveKitConfig() {
        kitConfig.saveDefaultConfig();
    }

}
