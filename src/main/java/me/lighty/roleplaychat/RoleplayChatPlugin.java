package me.lighty.roleplaychat;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Getter;
import me.lighty.roleplaychat.commands.*;
import me.lighty.roleplaychat.listeners.ChatListener;
import me.lighty.roleplaychat.listeners.JoinListener;
import me.lighty.roleplaychat.objects.Channel;
import me.lighty.roleplaychat.utils.Methods;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public final class RoleplayChatPlugin extends JavaPlugin {

    @Getter private static RoleplayChatPlugin roleplayChatPlugin;
    @Getter private static FileConfiguration database;
    @Getter private static LuckPerms luckPermsAPI;
    @Getter private static ArrayList<Channel> channels;
    @Getter private static HashMap<Player, Channel> playerChannels;
    @Getter private static HashMap<Player, Boolean> playerGlobalToggle;
    @Getter private static HashMap<Player, Boolean> loreSpectateToggle;
    @Getter private static HashMap<Player, String> loreSpectateMode;

    @Override
    public void onEnable() {
        // Plugin startup logic
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) luckPermsAPI = provider.getProvider();

        roleplayChatPlugin = this;
        roleplayChatPlugin.saveDefaultConfig();
        database = roleplayChatPlugin.getConfig();
        playerChannels = new HashMap<>();
        playerGlobalToggle = new HashMap<>();
        loreSpectateToggle = new HashMap<>();
        loreSpectateMode = new HashMap<>();

        channels = new ArrayList<>();
        channels.add(new Channel("Global", "", -1));
        channels.add(new Channel("Local", "", 15));

        for(Player player : Bukkit.getOnlinePlayers()) {
            playerChannels.put(player, Methods.getChannel("Global"));
            playerGlobalToggle.put(player, true);
            loreSpectateToggle.put(player, false);
            loreSpectateMode.put(player, "local");
        }

        this.getServer().getPluginManager().registerEvents(new ChatListener(), this);
        this.getServer().getPluginManager().registerEvents(new JoinListener(), this);

        this.getCommand("globaltoggle").setExecutor(new GlobalToggleCommand());
        this.getCommand("global").setExecutor(new GlobalCommand());
        this.getCommand("local").setExecutor(new LocalCommand());
        this.getCommand("lorespectate").setExecutor(new LoreSpectateCommand());
        this.getCommand("setprefixrp").setExecutor(new SetPrefixRPCommand());
        this.getCommand("setsuffixrp").setExecutor(new SetSuffixRPCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
