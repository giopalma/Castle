package me.dragospiro98.castle.bukkit.commands;

import dev.jorel.commandapi.CommandAPICommand;
import me.dragospiro98.castle.bukkit.CastlePlugin;
import me.dragospiro98.castle.bukkit.data.User;
import me.dragospiro98.castle.bukkit.managers.PlayerManager;
import me.dragospiro98.castle.bukkit.messages.Message;
import me.dragospiro98.castle.bukkit.nbteditor.NBTEditor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class RootUserCommads {
    private CastlePlugin plugin;

    public RootUserCommads(CastlePlugin plugin){
       this.plugin = plugin;
   }

   public void runRootUserCommands(){
        new CommandAPICommand("clan")
                .withSubcommand(onCastleCommand())
                .register();
   }

   private CommandAPICommand onCastleCommand(){
       return new CommandAPICommand("castle")
               .withPermission("parties.test.castle")
               .withSubcommand(onCastleCreateCommand());
   }

   private CommandAPICommand onCastleCreateCommand(){
        return new CommandAPICommand("create")
                .executesPlayer(((player, args) -> {
                    User user = PlayerManager.getInstance().getPlayer(player.getUniqueId());
                    // Controllare se è in un clan
                    if(!(user.isInClan())){
                        user.sendMessage(Message.NOT_IN_CLAN);
                        return;
                    }
                    // Controllare se è leader del clan
                    if(!(user.id.equals(user.getClan().getLeader()))){
                        user.sendMessage(Message.NOT_LEADER);
                        return;
                    }
                    // Controllare se il giocatore ha l'inventario pieno
                    if (player.getInventory().firstEmpty() == -1){
                        user.sendMessage(Message.FULL_INVENTORY);
                        return;
                    }
                    // Dare il blocco dorato al giocatore, il blocco non si può buttare, bisogna controllare se ha spazio nell'inventario
                    ItemStack guardian_spawner = getGuardianSpawner();
                    player.getInventory().addItem(guardian_spawner);
                    // Creare un comando /clan castle cancel per annullare l'operazione, rimuovere il blocco dorato.
                    // Al posizionamento del blocco dorato si deve creare un claim
                    // si deve posizionare un guardiano che attacca tutti i nemici
                }));
   }

   private ItemStack getGuardianSpawner(){
        ItemStack b = new ItemStack(Material.GOLD_BLOCK);
        ItemMeta meta = b.getItemMeta();
        String name = plugin.getConfig().getString("messages.guardian_spawner.block_name");
        meta.setDisplayName(name);
        List<String> lore = plugin.getConfig().getStringList("messages.guardian_spawner.description");
        meta.setLore(lore);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.addEnchant(Enchantment.WATER_WORKER,1,true);
        b.setItemMeta(meta);

        return b;

   }
}
