package me.dragospiro98.castle.bukkit.data;

import com.alessiodp.parties.api.interfaces.Party;
import me.dragospiro98.castle.bukkit.managers.PlayerManager;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Clan {
    private final Party party;
    private int force;
    private final Set<Region> regions;

    public Clan(Party party){
        this.party = party;
        this.regions = new HashSet<>();
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
     * @return le regioni {@link Region} controllate dal clan
     */
    public Set<Region> getRegions() {
        return regions;
    }

    /**
     * Aggiunge una regione al clan
     * @param region {@link Region}
     */
    public void addRegion(Region region){
        if(region.getClanId() == null){
            region.setClanId(this.party.getId());
            regions.add(region);
        }

    }
}
