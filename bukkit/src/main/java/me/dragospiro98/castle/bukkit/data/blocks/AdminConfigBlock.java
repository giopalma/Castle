package me.dragospiro98.castle.bukkit.data.blocks;

import me.dragospiro98.castle.bukkit.CastlePlugin;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class AdminConfigBlock implements CustomBlock{

    @Override
    public ItemStack build(CastlePlugin plugin) {
        return new BlockBuilder(Material.GOLD_BLOCK)
                .build();
    }
}
