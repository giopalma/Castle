package me.dragospiro98.castle.bukkit.data;

import me.dragospiro98.castle.bukkit.CastlePlugin;
import me.dragospiro98.castle.bukkit.messages.Message;
import org.bukkit.Bukkit;

import java.util.UUID;

public class User {

    public final UUID id;
    private CastlePlugin plugin;

    private int trophies = 0;
    private Clan clan;

    public User(UUID id, CastlePlugin plugin){
        this.plugin = plugin;
        this.id = id;
        clan = new Clan(plugin.getPartiesAPI().getPartyOfPlayer(id));
    }

    public User(UUID id, int trophies, CastlePlugin plugin){
        this.plugin = plugin;
        this.id = id;
        this.trophies = trophies;
        clan = new Clan(plugin.getPartiesAPI().getPartyOfPlayer(id));
    }

    public int getTrophies() {
        return trophies;
    }
    public void setTrophies(int trophies) {
        this.trophies = trophies;
    }

    public void addTrophies(Object n) {
        if (n instanceof Integer){
            this.trophies += (Integer) n;
        }
    }

    public boolean isInClan(){
        return plugin.getPartiesAPI().isPlayerInParty(id);
    }

    public Clan getClan() {
        return new Clan(plugin.getPartiesAPI().getPartyOfPlayer(id));
    }

    public void sendMessage(Message message) {
        plugin.getMessageHandler().sendMessage(Bukkit.getPlayer(id),message);
    }

    public void sendMessage(String string){
        Bukkit.getPlayer(id).sendMessage(string);
    }
}
