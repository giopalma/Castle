package me.dragospiro98.castle.bukkit.data;

import java.util.UUID;

public class Region {
    private final UUID uuid;

    private String world;
    private UUID clanId;
    private int x, z;

    public Region(UUID uuid){
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public UUID getClanId() {
        return clanId;
    }

    public void setClanId(UUID clanId) {
        this.clanId = clanId;
    }
}
