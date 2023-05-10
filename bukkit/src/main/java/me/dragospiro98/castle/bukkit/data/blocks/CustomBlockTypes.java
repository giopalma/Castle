package me.dragospiro98.castle.bukkit.data.blocks;

import me.dragospiro98.castle.bukkit.CastlePlugin;
import org.bukkit.inventory.ItemStack;

public enum CustomBlockTypes {

    GUARDIAN_SPAWNER(new GuardianSpawner()),
    ADMIN_CONFIG_BLOCK(new AdminConfigBlock());
    public final CustomBlock customBlock;
    CustomBlockTypes(CustomBlock customBlock){
        this.customBlock = customBlock;
    }

    public ItemStack build(CastlePlugin plugin){
        return customBlock.build(plugin);
    }
}
