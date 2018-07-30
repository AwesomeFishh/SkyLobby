package me.awesomefishh.skylobby.listeners;

import me.awesomefishh.skylobby.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class JoinListener implements Listener {

    private Main plugin;

    public JoinListener() {
        this.plugin = Main.getInstance();
    }

    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        plugin.getDatabaseManager().checkPlayer(player);

    }

}
