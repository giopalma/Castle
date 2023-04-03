package me.dragospiro98.castle.bukkit.placeholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.dragospiro98.castle.bukkit.CastlePlugin;
import me.dragospiro98.castle.bukkit.data.Clan;
import me.dragospiro98.castle.bukkit.managers.PlayerManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Expansion extends PlaceholderExpansion {

    private CastlePlugin plugin;

    public Expansion(CastlePlugin plugin){
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "castle";
    }

    @Override
    public @NotNull String getAuthor() {
        return "DragoSpiro98";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        if(params.equalsIgnoreCase("trophies")) {
            return "" + PlayerManager.getInstance().getPlayer(player.getUniqueId()).getTrophies();
        }

        if(params.equalsIgnoreCase("clan_trophies")) {
            Clan clan = PlayerManager.getInstance().getPlayer(player.getUniqueId()).getClan();
            if (PlayerManager.getInstance().getPlayer(player.getUniqueId()).isInClan()){
                return "" + PlayerManager.getInstance().getPlayer(player.getUniqueId()).getClan().getTrophies();
            }
        }
        return null;
    }
}
