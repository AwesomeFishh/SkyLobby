package me.awesomefishh.skylobby.commands;

import org.bukkit.command.CommandSender;

public abstract class SubCommand {

    public abstract void onCommand(CommandSender sender, String[] args);

    public abstract String name();

    public abstract String[] aliases();

}
