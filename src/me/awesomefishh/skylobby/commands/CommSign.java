package me.awesomefishh.skylobby.commands;

import me.awesomefishh.skylobby.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CommSign extends SubCommand {

    private Main plugin;

    public CommSign() {
        this.plugin = Main.getInstance();
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(plugin.getPrefix() + ChatColor.RED + "Only players can use this command!");
            return;
        }

        Player player = (Player) sender;
        UUID uuid = player.getUniqueId();

        if (!player.hasPermission("skylobby.sign.create")) {
            player.sendMessage(plugin.getPrefix() + ChatColor.RED + "You don't have permission to do that!");
            return;
        }

        if (args.length != 2) {
            player.sendMessage(plugin.getPrefix() + ChatColor.RED + "Invalid args! /sl help");
            return;
        }

        if (!player.getTargetBlock(null, 10).getType().equals(Material.WALL_SIGN)) {
            player.sendMessage(plugin.getPrefix() + ChatColor.RED + "Please look at a sign!");
            return;
        }

        String signName = args[0];
        String serverName = args[1];

        Location loc = player.getTargetBlock(null, 10).getLocation();
        plugin.getConfig().set("signs." + signName + ".server", serverName);
        plugin.getConfig().set("signs." + signName + ".world", loc.getWorld().getName());
        plugin.getConfig().set("signs." + signName + ".X", loc.getX());
        plugin.getConfig().set("signs." + signName + ".Y", loc.getY());
        plugin.getConfig().set("signs." + signName + ".Z", loc.getZ());
        plugin.saveConfig();

        player.sendMessage(plugin.getPrefix() + ChatColor.GREEN + "Successfully set this sign to display info about " + serverName + "!");

    }

    @Override
    public String name() {
        return plugin.getCommandManager().sign;
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
