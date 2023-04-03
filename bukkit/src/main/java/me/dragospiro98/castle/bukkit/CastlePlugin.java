package me.dragospiro98.castle.bukkit;

import com.alessiodp.parties.api.Parties;
import com.alessiodp.parties.api.interfaces.PartiesAPI;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIConfig;
import me.dragospiro98.castle.bukkit.commands.RootUserCommads;
import me.dragospiro98.castle.bukkit.data.storage.H2StorageProvider;
import me.dragospiro98.castle.bukkit.data.storage.MySQLStorageProvider;
import me.dragospiro98.castle.bukkit.data.storage.StorageProvider;
import me.dragospiro98.castle.bukkit.listeners.PlayerListener;
import me.dragospiro98.castle.bukkit.listeners.TrophiesListener;
import me.dragospiro98.castle.bukkit.managers.PlayerManager;
import me.dragospiro98.castle.bukkit.messages.MessageHandler;
import me.dragospiro98.castle.bukkit.placeholder.Expansion;
import me.ryanhamshire.GriefPrevention.GriefPrevention;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

public final class CastlePlugin extends JavaPlugin implements Listener {
    PartiesAPI partiesAPI = null;
    private MessageHandler messageHandler;

    private BukkitAudiences adventure;
    private MiniMessage miniMessage;
    private boolean onGriefPrevention;

    // Storage
    private StorageProvider storage;

    @Override
    public void onLoad() {
        super.onLoad();
        CommandAPI.onLoad(new CommandAPIConfig());

        new RootUserCommads(this).runRootUserCommands();
    }

    @Override
    public void onEnable() {
        // Config
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdir();
        }
        int oldVersion= getConfig().getInt("config_version");
        new ConfigUpdater(this).checkUpdate(oldVersion);

        this.saveDefaultConfig();



        // Adventure & MiniMessage
        this.miniMessage = MiniMessage.miniMessage();
        this.adventure = BukkitAudiences.create(this);
        // MessageHandler
        this.messageHandler = new MessageHandler(this);


        // Other plugins
        if (checkPluginEnabled("Parties")){
            partiesAPI = Parties.getApi();
        }

        if (checkPluginEnabled("PlaceholderAPI")) {
            new Expansion(this).register();
        }

        if (checkPluginEnabled("GriefPrevention")) {
            onGriefPrevention = true;
        }

        // Creazione database
        this.storage = getDatabase();

        // PlayerManager
        new PlayerManager(this);

        // Commands
        CommandAPI.onEnable(this);

        // Listeners
        registerListeners();


    }

    private StorageProvider getDatabase() {
        String databaseType = getConfig().getString("storage.type");
        if (databaseType == null){
            getLogger().warning("Unable to get database type from config file, check the config.yml! The plugin will uses H2 Database as default");
        } else {
            databaseType = databaseType.toLowerCase();
        }
        switch (databaseType){
            case "mysql":
                getLogger().info("MySQL is used");
                return new MySQLStorageProvider(this);
            default:
                getLogger().info("H2 is used");
                return new H2StorageProvider(this);
        }
    }

    public void registerListeners(){
        getServer().getPluginManager().registerEvents(new PlayerListener(this),this);
        getServer().getPluginManager().registerEvents(new TrophiesListener(this),this);
    }

    @Override
    public void onDisable() {
        storage.close();
        if(this.adventure != null) {
            this.adventure.close();
            this.adventure = null;
        }
    }

    /**
     * Controlla se un plugin necessario è attivo nel server
     * @param plugin nome del plugin da controllare
     * @return true se il plugin è presente ed è attivo, altrimenti ritorna false
     */
    private boolean checkPluginEnabled(String plugin){
        if (getServer().getPluginManager().getPlugin(plugin) != null) {
            if (getServer().getPluginManager().getPlugin(plugin).isEnabled()) {
                return true;
            }
        }
        return false;
    }

    public PartiesAPI getPartiesAPI() {
        return partiesAPI;
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    public @NonNull BukkitAudiences getAdventure() {
        if(this.adventure == null) {
            throw new IllegalStateException("Tried to access Adventure when the plugin was disabled!");
        }
        return this.adventure;
    }
    public MiniMessage getMiniMessage() {
        return miniMessage;
    }

    public StorageProvider getStorage() {
        return this.storage;
    }
}
