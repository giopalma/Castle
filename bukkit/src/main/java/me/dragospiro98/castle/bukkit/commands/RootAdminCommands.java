package me.dragospiro98.castle.bukkit.commands;

import dev.jorel.commandapi.CommandAPICommand;
import me.dragospiro98.castle.bukkit.CastlePlugin;
import me.dragospiro98.castle.bukkit.data.blocks.BlockBuilder;
import me.dragospiro98.castle.bukkit.data.blocks.CustomBlockTypes;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

public class RootAdminCommands {

    private CastlePlugin plugin;

    public RootAdminCommands(CastlePlugin plugin){
        this.plugin = plugin;
    }

    public void runRootAdminCommands(){
        new CommandAPICommand("castle")
                .withPermission("castle.admin")
                .withSubcommand(onCreate())
                .register();
    }

    public CommandAPICommand onCreate(){
        return new CommandAPICommand("create")
                .withPermission("castle.admin.create")
                .executesPlayer((player, commandArguments) -> {
                    // Dare un blocco di configurazione al'admin

                    ItemStack configBlock = new BlockBuilder(CustomBlockTypes.ADMIN_CONFIG_BLOCK,plugin).build();

                });
    }



}
