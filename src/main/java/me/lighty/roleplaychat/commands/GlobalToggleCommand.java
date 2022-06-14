package me.lighty.roleplaychat.commands;

import me.lighty.roleplaychat.RoleplayChatPlugin;
import me.lighty.roleplaychat.utils.Methods;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GlobalToggleCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;
        if(!label.equalsIgnoreCase("globaltoggle")) return false;

        Player player = (Player) sender;
        Boolean toggleStatus = RoleplayChatPlugin.getPlayerGlobalToggle().get(player);
        RoleplayChatPlugin.getPlayerGlobalToggle().put(player, !toggleStatus);
        toggleStatus = RoleplayChatPlugin.getPlayerGlobalToggle().get(player);

        player.sendMessage(Methods.chatColor("&4&l[&6&lNSB&4&l] &aYou toggled the status of global chat to " + toggleStatus + "!"));

        if(Methods.getChannel(player).getName().equalsIgnoreCase("global")) {
            if(!toggleStatus) {
                RoleplayChatPlugin.getPlayerChannels().put(player, Methods.getChannel("local"));
            }
        }

        return true;
    }
}
