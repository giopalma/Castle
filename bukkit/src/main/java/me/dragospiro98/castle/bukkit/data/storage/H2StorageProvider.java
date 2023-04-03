package me.dragospiro98.castle.bukkit.data.storage;

import me.dragospiro98.castle.bukkit.CastlePlugin;

import java.sql.DriverManager;
import java.sql.SQLException;

public class H2StorageProvider extends SQLStorageProvider{

    public H2StorageProvider(CastlePlugin p) {
        super(p);
    }

    @Override
    void connect() throws SQLException {
        String url = "jdbc:h2:" + super.plugin.getDataFolder().getAbsolutePath() + "/database";
        super.plugin.getLogger().warning(url);
        if (!isConnected()){
            setConnection(DriverManager.getConnection(url));
        }
    }
}
