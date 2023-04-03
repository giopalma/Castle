package me.dragospiro98.castle.bukkit.messages;

import me.dragospiro98.castle.bukkit.CastlePlugin;
import net.kyori.adventure.text.Component;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class MessageHandler {
    private CastlePlugin plugin;

    public MessageHandler(CastlePlugin plugin){
        this.plugin = plugin;
    }

    public Component getText(Message message){
        FileConfiguration config = plugin.getConfig();
        String messageString = config.getString(message.getPath());
        if (messageString != null){
            return plugin.getMiniMessage().deserialize(messageString);
        }
        return Component.text("");
    }

    public void sendMessage(CommandSender sender, Message message){
        plugin.getAdventure().sender(sender).sendMessage(getText(message));
    }
}
