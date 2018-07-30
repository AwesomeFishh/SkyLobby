package me.awesomefishh.skylobby.commands;

import me.awesomefishh.skylobby.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommHelp extends SubCommand {

    private Main plugin;

    public CommHelp() {
        this.plugin = Main.getInstance();
    }

    @Override
    public void onCommand(CommandSender sender, String[] args) {

        if (args.length != 0) {
            sender.sendMessage(plugin.getPrefix() + ChatColor.RED + "Invalid args! /sl help");
            return;
        }

        sender.sendMessage(plugin.getPrefix() + ChatColor.GOLD + "/sl help - " + ChatColor.YELLOW + "Displays this message!");
        if(sender.hasPermission("skylobby.signs.create"))
            sender.sendMessage(plugin.getPrefix() + ChatColor.GOLD + "/sl sign <server> - " + ChatColor.YELLOW + "Create a sign which displays information about given server!");

    }

    @Override
    public String name() {
        return plugin.getCommandManager().help;
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
