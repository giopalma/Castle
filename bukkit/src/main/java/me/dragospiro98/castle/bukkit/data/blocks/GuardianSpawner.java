package me.dragospiro98.castle.bukkit.data.blocks;

import me.dragospiro98.castle.bukkit.CastlePlugin;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class GuardianSpawner implements CustomBlock{
    @Override
    public ItemStack build(CastlePlugin plugin) {
        return new BlockBuilder(Material.GOLD_BLOCK)
                .name(plugin.getConfig().getString("messages.guardian_spawner.block_name"))
                .lore(plugin.getConfig().getStringList("messages.guardian_spawner.description"))
                .enchant(Enchantment.OXYGEN,1,true)
                .itemFlag(ItemFlag.HIDE_ENCHANTS)
                .build();
    }
}
