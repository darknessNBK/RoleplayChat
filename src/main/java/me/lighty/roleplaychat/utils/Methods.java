package me.lighty.roleplaychat.utils;

import me.lighty.roleplaychat.RoleplayChatPlugin;
import me.lighty.roleplaychat.objects.Channel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Methods {

    public static String chatColor(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static Channel getChannel(Player player) {
        return RoleplayChatPlugin.getPlayerChannels().get(player);
    }

    public static Channel getChannel(String channelName) {
        return RoleplayChatPlugin.getChannels().stream().filter(channel -> channel.getName().equalsIgnoreCase(channelName)).findFirst().get();
    }

    public static boolean getGlobalToggle(Player player) {
        return RoleplayChatPlugin.getPlayerGlobalToggle().get(player);
    }

    public static boolean getLoreSpectateToggle(Player player) {
        return RoleplayChatPlugin.getLoreSpectateToggle().get(player);
    }

    public static String getLoreSpectateMode(Player player) {
        return RoleplayChatPlugin.getLoreSpectateMode().get(player);
    }

    public static void sendChatInRadius(Player player, Integer radius, String msg) {
        for(Entity entity : player.getNearbyEntities(radius, radius, radius)) {
            if(entity instanceof Player) entity.sendMessage(chatColor(msg));
        }
        for(Player loopPlayer : Bukkit.getOnlinePlayers()) {
            if(getLoreSpectateToggle(player) && getLoreSpectateMode(loopPlayer).equalsIgnoreCase("global") && loopPlayer.getLocation().distance(player.getLocation()) > radius) {
                player.sendMessage(chatColor( msg));
            }
        }
    }

    public static void sendLoreSpectators(Player player, Integer radius, String msg) {
        for(Entity entity : player.getNearbyEntities(radius, radius, radius)) {
            if(entity instanceof Player && getLoreSpectateToggle((Player) entity) && getLoreSpectateMode((Player) entity).equalsIgnoreCase("local") ) entity.sendMessage(chatColor( msg));
        }
    }

    public static void setPrefix(Player player, String prefix) {
        FileConfiguration config = RoleplayChatPlugin.getDatabase();
        config.set("player-data." + player.getUniqueId().toString() + ".prefix", prefix);
        RoleplayChatPlugin.getRoleplayChatPlugin().saveConfig();
    }

    public static void setSuffix(Player player, String suffix) {
        FileConfiguration config = RoleplayChatPlugin.getDatabase();
        config.set("player-data." + player.getUniqueId().toString() + ".suffix", suffix);
        RoleplayChatPlugin.getRoleplayChatPlugin().saveConfig();
    }

    public static String getPrefix(Player player) {
        FileConfiguration config = RoleplayChatPlugin.getDatabase();
        String prefix = config.getString("player-data." + player.getUniqueId().toString() + ".prefix");
        if(prefix == null) prefix = "";
        return chatColor(prefix);
    }

    public static String getSuffix(Player player) {
        FileConfiguration config = RoleplayChatPlugin.getDatabase();
        String suffix = config.getString("player-data." + player.getUniqueId().toString() + ".suffix");
        if(suffix == null) suffix = "";
        return chatColor(suffix);
    }

    public static void setName(Player player, String name) {
        FileConfiguration config = RoleplayChatPlugin.getDatabase();
        config.set("player-data." + player.getUniqueId().toString() + ".name", name);
        RoleplayChatPlugin.getRoleplayChatPlugin().saveConfig();
    }

    public static String getName(Player player) {
        FileConfiguration config = RoleplayChatPlugin.getDatabase();
        String name = config.getString("player-data." + player.getUniqueId().toString() + ".name");
        if(name == null) name = "";
        return chatColor(name);
    }

}
