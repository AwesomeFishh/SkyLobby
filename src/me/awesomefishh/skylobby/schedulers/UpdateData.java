package me.awesomefishh.skylobby.schedulers;

import me.awesomefishh.skylobby.Main;
import me.awesomefishh.skylobby.game.GameData;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UpdateData {

    private Main plugin;

    public UpdateData() {
        this.plugin = Main.getInstance();
    }

    public void initiate() {

        new BukkitRunnable() {

            @Override
            public void run() {

                List<String> serverList = new ArrayList<>();
                for (String signName : plugin.getConfig().getConfigurationSection("signs").getKeys(false)) {
                    serverList.add(plugin.getConfig().getString("signs." + signName + ".server"));
                }

                for (String serverName : serverList) {
                    try {

                        PreparedStatement statement = plugin.getDatabaseManager().getConnection().prepareStatement("SELECT * FROM " + plugin.getDatabaseManager().getGameDataTable() + " WHERE SERVER='" + serverName + "'");
                        ResultSet resultSet = statement.executeQuery();

                        if (resultSet.next()) {

                            String gameState = resultSet.getString("GAMESTATE");
                            String chosenArena = resultSet.getString("ARENA");
                            int playersAlive = resultSet.getInt("PLAYERSALIVE");
                            int playersMax = resultSet.getInt("PLAYERSMAX");
                            int countdownLeft = resultSet.getInt("COUNTDOWN");

                            if (plugin.getGameData().containsKey(serverName)) {

                                GameData gameData = plugin.getGameData().get(serverName);
                                gameData.setGameState(gameState);
                                gameData.setChosenArena(chosenArena);
                                gameData.setPlayersAlive(playersAlive);
                                gameData.setPlayersMax(playersMax);
                                gameData.setCountdownLeft(countdownLeft);

                            } else {
                                plugin.getGameData().put(serverName, new GameData(serverName, chosenArena, gameState, playersAlive, playersMax, countdownLeft));
                            }


                        } else {
                            plugin.getLogger().info(serverName + " does not exist in the database, ignoring!");
                        }

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }

        }.runTaskTimer(plugin, 5 * 20L, 20L);

    }

}
