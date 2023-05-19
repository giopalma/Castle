package me.dragospiro98.castle.bukkit.data;

import com.alessiodp.parties.api.interfaces.Party;
import com.sk89q.worldguard.protection.regions.ProtectedPolygonalRegion;
import me.dragospiro98.castle.bukkit.managers.PlayerManager;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Clan {
    private final Party party;
    private int force;
    private ProtectedPolygonalRegion region;

    public Clan(Party party, ProtectedPolygonalRegion region){
        this.party = party;
        this.region = region;
    }

    public Clan(Party party){
        this(party, null);
    }

    public int getTrophies() {
        int n = 0;
        for (UUID member : party.getMembers()){
            n += PlayerManager.getInstance().getPlayer(member).getTrophies();
        }
        return n;
    }

    /**
     * @return l'id del clan, è lo stesso di quello utilizzato dal plugin Parties
     */
    public UUID getId() {
        return this.party.getId();
    }

    /**
     * @return l'id del leader del clan
     */
    public UUID getLeader() {
        return party.getLeader();
    }

    /**
     * @return la region del clan
     */
    @Nullable public ProtectedPolygonalRegion getRegion() {
        return region;
    }

    /**
     * @param region la regione da settare
     */
    public void setRegion(ProtectedPolygonalRegion region) {
        this.region = region;
    }
}
