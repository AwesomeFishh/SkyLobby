package me.awesomefishh.skylobby.schedulers;

import me.awesomefishh.skylobby.Main;
import me.awesomefishh.skylobby.game.GameData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.scheduler.BukkitRunnable;

public class UpdateSigns {

    private Main plugin;

    public UpdateSigns() {
        this.plugin = Main.getInstance();
    }

    public void initiate() {

        new BukkitRunnable() {

            @Override
            public void run() {

                for (String signName : plugin.getConfig().getConfigurationSection("signs").getKeys(false)) {

                    Location location = new Location(
                            Bukkit.getWorld(plugin.getConfig().getString("signs." + signName + ".world")),
                            plugin.getConfig().getDouble("signs." + signName + ".X"),
                            plugin.getConfig().getDouble("signs." + signName + ".Y"),
                            plugin.getConfig().getDouble("signs." + signName + ".Z"));

                    Block block = location.getBlock();

                    if (!(block.getState() instanceof Sign)) {
                        plugin.getLogger().severe("There is no sign at " + signName + "'s location, ignoring...");
                        return;
                    }

                    String serverName = plugin.getConfig().getString("signs." + signName + ".server");

                    if (!plugin.getGameData().containsKey(serverName)) {
                        plugin.getLogger().severe("No GameData found for server " + serverName + " for sign " + signName + ", ignoring.");
                        return;
                    }

                    GameData gameData = plugin.getGameData().get(serverName);

                    String chosenArena = gameData.getChosenArena();
                    String gameState = gameData.getGameState();
                    int playersAlive = gameData.getPlayersAlive();
                    int playersMax = gameData.getPlayersMax();
                    int countdownLeft = gameData.getCountdownLeft();

                    Sign sign = (Sign) block.getState();
                    sign.setLine(0, ChatColor.GOLD + chosenArena);
                    sign.setLine(1, ChatColor.YELLOW + "" + playersAlive + "/" + playersMax);
                    sign.setLine(2, ChatColor.GRAY + gameState);
                    if (countdownLeft == 0) {
                        sign.setLine(3, ChatColor.GOLD + "==============");
                    } else {
                        sign.setLine(3, ChatColor.GOLD + "" + countdownLeft);
                    }
                    sign.update();

                }

            }

        }.runTaskTimer(plugin, 7L * 20L, 20L);

    }

}
