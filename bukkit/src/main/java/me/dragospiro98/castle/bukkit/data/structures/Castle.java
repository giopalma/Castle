package me.dragospiro98.castle.bukkit.data.structures;

import me.dragospiro98.castle.bukkit.data.Clan;
import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Il castello è la base principale del clan, questo può essere steso, acquistando chunk di claim
 * Il castello si crea nel momento in cui si posiziona il blocco dorato per spawnare il guardiano
 */
public class Castle implements Structure{
    private Location location = null;
    private  UUID uuid;
    private UUID clan;
    public Castle(Location location){
        this.location = location;
        this.uuid = UUID.randomUUID();
    }

    public Castle(Location location, UUID clan){
        this.location = location;
        this.clan = clan;
        this.uuid = UUID.randomUUID();
    }

    public void setHome(Location location){
        this.location = location;
    }

    @Override
    public UUID getUuid() {
        return this.uuid;
    }

    @Nullable
    @Override
    public UUID getClan() {
        return this.clan;
    }
}
