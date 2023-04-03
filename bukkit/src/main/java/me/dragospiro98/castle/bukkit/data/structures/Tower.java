package me.dragospiro98.castle.bukkit.data.structures;

import me.dragospiro98.castle.bukkit.data.Clan;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Tower implements Structure{
    private UUID uuid;
    private Clan clan;

    @Override
    public UUID getUuid() {
        return null;
    }

    @Nullable
    @Override
    public UUID getClan() {
        return null;
    }
}
