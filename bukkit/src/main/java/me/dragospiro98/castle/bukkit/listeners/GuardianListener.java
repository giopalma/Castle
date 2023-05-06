package me.dragospiro98.castle.bukkit.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class GuardianListener implements Listener {

    public void onGuardianBlockPlaced(BlockPlaceEvent placeEvent){
        ItemStack block = placeEvent.getItemInHand();
    }
}
