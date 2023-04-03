package me.dragospiro98.castle.bukkit.listeners;

import me.dragospiro98.castle.bukkit.CastlePlugin;
import me.dragospiro98.castle.bukkit.data.User;
import me.dragospiro98.castle.bukkit.managers.PlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    private final CastlePlugin plugin;

    public PlayerListener(CastlePlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    void onPlayerFirstJoin(PlayerJoinEvent event){
        PlayerManager.getInstance().addPlayer(event.getPlayer().getUniqueId());
    }
}
