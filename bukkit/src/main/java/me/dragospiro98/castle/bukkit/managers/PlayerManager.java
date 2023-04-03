package me.dragospiro98.castle.bukkit.managers;

import me.dragospiro98.castle.bukkit.CastlePlugin;
import me.dragospiro98.castle.bukkit.data.User;
import me.dragospiro98.castle.bukkit.data.storage.StorageProvider;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {
    private static PlayerManager instance;
    private final CastlePlugin plugin;
    private final Map<UUID, User> users = new HashMap<>();

    public PlayerManager(CastlePlugin plugin){
        this.plugin = plugin;
        plugin.getStorage().createUserTable();
        instance = this;
    }

    public void addPlayer(UUID id) {
        User user = users.get(id);
        if (user != null){
            return;
        }
        try {
            user = plugin.getStorage().getPlayerData(id);
            if (user == null){
                user = new User(id,plugin);
            }
        } catch (SQLException e) {
            plugin.getLogger().warning(e.getMessage());
            user = new User(id,plugin);
        }
        users.put(id,user);
    }

    public static PlayerManager getInstance() {
        return instance;
    }

    @Nullable public User getPlayer(UUID player) {
        return users.get(player);
    }

    public void close(StorageProvider storage) {
       users.forEach((storage::updateUser));
    }
}
