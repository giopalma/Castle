package me.dragospiro98.castle.bukkit.data;

import com.alessiodp.parties.api.interfaces.Party;
import me.dragospiro98.castle.bukkit.managers.PlayerManager;

import java.util.UUID;

public class Clan {
    private final Party party;
    // private final CastlePlugin plugin;
    private int force;

    public Clan(Party party){
        this.party = party;
        // this.plugin = plugin;
    }

    public int getTrophies() {
        int n = 0;
        for (UUID member : party.getMembers()){
            n += PlayerManager.getInstance().getPlayer(member).getTrophies();
        }
        return n;
    }

    /**
     * @return l'id del leader del clan
     */
    public UUID getLeader() {
        return party.getLeader();
    }
}
