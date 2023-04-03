package me.dragospiro98.castle.bukkit.listeners;

import me.dragospiro98.castle.bukkit.CastlePlugin;
import me.dragospiro98.castle.bukkit.managers.PlayerManager;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

public class TrophiesListener implements Listener {

    private final CastlePlugin plugin;

    public TrophiesListener(CastlePlugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    void onPlayerKillOther(PlayerDeathEvent event) {
            if (event.getEntity().getKiller() != null){
                UUID killerID = event.getEntity().getKiller().getUniqueId();
                PlayerManager.getInstance().getPlayer(killerID).addTrophies(plugin.getConfig().get("trophies.player_kill"));
            }
           }

    @EventHandler
    void onEntityKill(EntityDeathEvent event){
        if (!(event.getEntity() instanceof Player)){
            if (event.getEntity().getKiller() != null){
                UUID killerID = event.getEntity().getKiller().getUniqueId();
                AttributeInstance entityHealth = event.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH);
                PlayerManager.getInstance().getPlayer(killerID).addTrophies(Math.toIntExact(Math.round(entityHealth.getValue()/5)));
            }
        }
    }
}
