package me.lighty.roleplaychat.objects;

import lombok.Getter;
import me.lighty.roleplaychat.RoleplayChatPlugin;
import me.lighty.roleplaychat.utils.Methods;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;

public class Channel {

    @Getter public String name;
    @Getter public String prefix;
    @Getter public int radius;

    public Channel(String name, String prefix, Integer radius) {
        this.name = name;
        this.prefix = Methods.chatColor(prefix);
        this.radius = radius;
    }

    public String formatText(Player player, String text) {
        String formattedText = "";
        User playerUser = RoleplayChatPlugin.getLuckPermsAPI().getUserManager().getUser(player.getUniqueId());

        if(name.equalsIgnoreCase("global")) {
            String playerName = player.getName();
            String rank = RoleplayChatPlugin.getLuckPermsAPI().getGroupManager().getGroup(playerUser.getPrimaryGroup()).getCachedData().getMetaData().getPrefix();
            if(rank == null) rank = "";
            formattedText = Methods.chatColor(rank + " &7" + playerName + ": &r" + text);
        }

        return formattedText;
    }
}
