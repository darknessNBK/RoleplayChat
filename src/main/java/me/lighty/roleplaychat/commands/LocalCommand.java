package me.lighty.roleplaychat.commands;

import me.lighty.roleplaychat.RoleplayChatPlugin;
import me.lighty.roleplaychat.objects.Channel;
import me.lighty.roleplaychat.utils.Methods;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LocalCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;
        if(!label.equalsIgnoreCase("local")) return false;

        Player player = (Player) sender;
        Channel channel = Methods.getChannel(player);

        if(channel.getName().equalsIgnoreCase("local")) {
            player.sendMessage(Methods.chatColor("&c&lHey! &7You are already in the local chat!"));
            return true;
        } else {
            RoleplayChatPlugin.getPlayerChannels().put(player, Methods.getChannel("local"));
            player.sendMessage(Methods.chatColor("&4&l[&6&lNSB&4&l] &aSwitched to the local chat!"));
            return true;
        }
    }
}
