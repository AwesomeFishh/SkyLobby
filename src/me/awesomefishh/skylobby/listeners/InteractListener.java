package me.awesomefishh.skylobby.listeners;

import me.awesomefishh.skylobby.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {

    private Main plugin;

    public InteractListener() {
        this.plugin = Main.getInstance();
    }

    public void onInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
            return;

        if (!event.getClickedBlock().getType().equals(Material.WALL_SIGN)) {
            return;
        }

        Sign sign = (Sign) event.getClickedBlock().getState();
        Location location = sign.getLocation();

        for (String signName : plugin.getConfig().getConfigurationSection("signs").getKeys(false)) {

            World world = Bukkit.getWorld(plugin.getConfig().getString("signs." + signName + ".world"));
            double signX = plugin.getConfig().getDouble("signs." + signName + ".X");
            double signY = plugin.getConfig().getDouble("signs." + signName + ".Y");
            double signZ = plugin.getConfig().getDouble("signs." + signName + ".Z");

            if (!location.getWorld().equals(world))
                return;
            if (location.getX() != signX)
                return;
            if (location.getY() != signY)
                return;
            if (location.getZ() != signZ)
                return;

            String serverName = plugin.getConfig().getString("signs." + signName + ".server");

        }

    }

}
