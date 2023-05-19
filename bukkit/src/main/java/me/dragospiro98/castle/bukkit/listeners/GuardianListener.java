package me.dragospiro98.castle.bukkit.listeners;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector2;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.protection.regions.ProtectedPolygonalRegion;
import me.dragospiro98.castle.bukkit.CastlePlugin;
import me.dragospiro98.castle.bukkit.data.Clan;
import me.dragospiro98.castle.bukkit.data.User;
import me.dragospiro98.castle.bukkit.data.blocks.CustomBlockTypes;
import me.dragospiro98.castle.bukkit.messages.Message;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.Arrays;

public class GuardianListener implements Listener {
    private final CastlePlugin plugin;

    public GuardianListener(CastlePlugin plugin) {
        this.plugin = plugin;
    }

    public void onGuardianBlockPlaced(BlockPlaceEvent placeEvent){
        ItemStack blockItem = placeEvent.getItemInHand();

        //Controlla se il blocco piazzato e' valido
        if(!blockItem.isSimilar(CustomBlockTypes.GUARDIAN_SPAWNER.build(plugin))){
            return;
        }
        Player player = placeEvent.getPlayer();
        Clan clan = null;
        User user = null;
        try {
            user = plugin.getStorage().getPlayerData(player.getUniqueId());
            clan = plugin.getStorage().getPlayerData(player.getUniqueId()).getClan();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(clan == null) return;
        placeEvent.setCancelled(true);
        if(user == null) return;

        //Controlla se il player e' il leader del clan
        if(!clan.getLeader().equals(player.getUniqueId())){
            user.sendMessage(Message.NOT_LEADER);
            return;
        }

        //Controlla se il mondo in cui e' stato piazzato il blocco e' valido
        if(!placeEvent.getPlayer().getWorld().equals(Bukkit.getWorld(plugin.getConfig().getString("castle.world")))) return;

        Block placedBlock = placeEvent.getBlockPlaced();
        Chunk chunk = placedBlock.getChunk();
        int minX = chunk.getX()<<4, minZ = chunk.getZ()<<4;
        int maxX = minX+16, maxZ = minZ+16;
        BlockVector2 min = BlockVector2.at(minX, minZ);
        BlockVector2 max = BlockVector2.at(maxX, maxZ);
        ProtectedPolygonalRegion region = new ProtectedPolygonalRegion("region-" + clan.getId().toString(), Arrays.asList(min, max),
                chunk.getWorld().getMinHeight(), chunk.getWorld().getMaxHeight());
        plugin.getWorldGuardAPI().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(placeEvent.getPlayer().getWorld()))
                .addRegion(region);
        clan.setRegion(region);
        placeEvent.getPlayer().setItemInHand(null);




    }
}
