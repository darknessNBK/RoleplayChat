package me.lighty.roleplaychat.listeners;

import me.lighty.roleplaychat.RoleplayChatPlugin;
import me.lighty.roleplaychat.utils.Methods;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event) {
        RoleplayChatPlugin.getPlayerChannels().put(event.getPlayer(), Methods.getChannel("Global"));
        RoleplayChatPlugin.getPlayerGlobalToggle().put(event.getPlayer(),true);
    }
}
