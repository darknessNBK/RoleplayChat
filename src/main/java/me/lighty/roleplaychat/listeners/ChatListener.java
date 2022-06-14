package me.lighty.roleplaychat.listeners;

import me.lighty.roleplaychat.RoleplayChatPlugin;
import me.lighty.roleplaychat.objects.Channel;
import me.lighty.roleplaychat.utils.Methods;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void playerChatEvent(PlayerChatEvent event) {
        event.setCancelled(true);

        Player player = event.getPlayer();
        Channel channel = Methods.getChannel(player);

        if(channel.getName().equalsIgnoreCase("global")) Bukkit.getOnlinePlayers().stream().filter(Methods::getGlobalToggle).forEach(player1 -> player1.sendMessage(channel.formatText(player, event.getMessage())));
        else if(channel.getName().equalsIgnoreCase("local")) {
            int radius = channel.getRadius();

            String rank = Methods.getPrefix(player);
            String faction = Methods.getSuffix(player);

            if(rank == null) rank = "";
            if(faction == null) faction = "";

            String character = player.getDisplayName();
            String nick = player.getName();

            String action = event.getMessage().substring(0, 1);
            String afterAction = event.getMessage().substring(1);

            // Emote
            if(action.equalsIgnoreCase("+")) {
                String msg = "&5&o" + character + " &5&o" + afterAction;
                Methods.sendChatInRadius(player, radius, msg);
                player.sendMessage(Methods.chatColor(msg));
                Methods.sendLoreSpectators(player, 100, msg);

            }

            // Whisper
            else if(action.equalsIgnoreCase("*")) {
                String msg = rank + " " + character + " " + faction + " &7&owhispers: &r\"" + afterAction + "\"";
                Methods.sendChatInRadius(player, 6, msg);
                player.sendMessage(Methods.chatColor(msg));
                Methods.sendLoreSpectators(player, 100, msg);
            }

            // Shout
            else if(action.equalsIgnoreCase("!")) {
                String msg = rank + " " + character + " " + faction + " &7&oshouts: &r\"" + afterAction + "\"";
                Methods.sendChatInRadius(player, 30, msg);
                player.sendMessage(Methods.chatColor(msg));
                Methods.sendLoreSpectators(player, 100, msg);
            }

            // Chat
            else if(action.equalsIgnoreCase("\"")) {
                String msg = rank + " " + character + " " + faction + ": &r\"" + afterAction + "\"";
                Methods.sendChatInRadius(player, 18, msg);
                player.sendMessage(Methods.chatColor(msg));
                Methods.sendLoreSpectators(player, 100, msg);
            }

            // OOC
            else {
                String msg = "&7&o" + nick + ": " + event.getMessage();
                Methods.sendChatInRadius(player, 18, msg);
                player.sendMessage(Methods.chatColor(msg));
                Methods.sendLoreSpectators(player, 100, msg);
            }
        }
    }

}
