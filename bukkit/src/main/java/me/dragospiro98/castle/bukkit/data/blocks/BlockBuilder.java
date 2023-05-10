package me.dragospiro98.castle.bukkit.data.blocks;

import me.dragospiro98.castle.bukkit.CastlePlugin;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class BlockBuilder {
    private final ItemMeta meta;
    private final Material material;

    private ItemStack item = null;
    public BlockBuilder(Material material){
        this.material = material;
        this.meta = null;
    }

    public BlockBuilder(CustomBlockTypes type, CastlePlugin plugin){
        this.item = type.build(plugin);
        this.meta = item.getItemMeta();
        this.material = item.getType();
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
        if (item == null){
            item = new ItemStack(material);
            item.setItemMeta(meta);
        }
        return item;
    }
}
