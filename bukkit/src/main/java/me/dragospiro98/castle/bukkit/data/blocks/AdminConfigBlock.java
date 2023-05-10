package me.dragospiro98.castle.bukkit.data.blocks;

import me.dragospiro98.castle.bukkit.CastlePlugin;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import java.util.Arrays;

public class AdminConfigBlock implements CustomBlock{

    @Override
    public ItemStack build(CastlePlugin plugin) {
        return new BlockBuilder(Material.PRISMARINE_BRICKS)
                .name("CASTLE CONFIGURE")
                .lore(Arrays.asList("Posiziona questo blocco", "per creare la region della fortezza")) //TODO: Leggerli dal file config
                .enchant(Enchantment.OXYGEN,1,true)
                .itemFlag(ItemFlag.HIDE_ENCHANTS)
                .build();
    }
}
