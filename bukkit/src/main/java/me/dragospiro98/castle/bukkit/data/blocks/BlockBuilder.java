package me.dragospiro98.castle.bukkit.data.blocks;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class BlockBuilder {
    private final ItemMeta meta;
    private final Material material;
    public BlockBuilder(Material material){
        this.material = material;
        this.meta = null;
    }
    public BlockBuilder name(String name){
        meta.setDisplayName(name);
        return this;
    }

    public BlockBuilder lore(List<String> lore){
        meta.setLore(lore);
        return this;
    }
    public BlockBuilder enchant(Enchantment enchantment, int level, boolean ignoreLevelRestriction){
        meta.addEnchant(enchantment,level,ignoreLevelRestriction);
        return this;
    }
    public BlockBuilder itemFlag(ItemFlag flag){
        meta.addItemFlags(flag);
        return this;
    }
    public ItemStack build(){
        ItemStack b = new ItemStack(material);
        b.setItemMeta(meta);
        return b;
    }
}
