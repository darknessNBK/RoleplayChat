package me.lighty.roleplaychat.commands;

import me.lighty.roleplaychat.utils.Methods;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SetPrefixRPCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;
        if(!label.equalsIgnoreCase("setprefixrp")) return false;
        Player player = (Player) sender;

        if(args.length < 2) {
            player.sendMessage(Methods.chatColor("&c&lHey! &r&7Correct usage: /setprefixrp [player] [prefix]"));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            builder.append(" " + args[i]);
        }
        String prefix = builder.toString();

        if(target == null) {
            player.sendMessage(Methods.chatColor("&c&lHey! &r&7The given player is not online!"));
            return true;
        }

        Methods.setPrefix(target, prefix);
        player.sendMessage(Methods.chatColor("&4&l[&6&lNSB&4&l] &aYou've set the prefix of " + target.getName() + " to: " + prefix));
        target.sendMessage(Methods.chatColor("&4&l[&6&lNSB&4&l] &aYour prefix has changed to: " + prefix));
        return true;
    }
}
