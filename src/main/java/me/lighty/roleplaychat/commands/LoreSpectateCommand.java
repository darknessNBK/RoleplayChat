package me.lighty.roleplaychat.commands;

import me.lighty.roleplaychat.RoleplayChatPlugin;
import me.lighty.roleplaychat.utils.Methods;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class LoreSpectateCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) return false;
        if(!label.equalsIgnoreCase("lorespectate")) return false;
        Player player = (Player) sender;

        String mode = "local";
        if(args.length > 0 && args[0].equalsIgnoreCase("global")) mode = "global";

        Boolean toggleStatus = RoleplayChatPlugin.getLoreSpectateToggle().get(player);
        RoleplayChatPlugin.getLoreSpectateToggle().put(player, !toggleStatus);
        toggleStatus = RoleplayChatPlugin.getLoreSpectateToggle().get(player);

        if(toggleStatus) {
            RoleplayChatPlugin.getLoreSpectateMode().put(player, mode);
            player.sendMessage(Methods.chatColor("&4&l[&6&lNSB&4&l] &aYou are now spectating roleplay " + RoleplayChatPlugin.getLoreSpectateMode().get(player) + "ly!"));
            return true;
        } else {
            player.sendMessage(Methods.chatColor("&4&l[&6&lNSB&4&l] &cYou are no longer spectating roleplay!"));
            return true;
        }
    }
}
