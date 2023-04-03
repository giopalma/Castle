package me.dragospiro98.castle.bukkit.data.storage;

import me.dragospiro98.castle.bukkit.CastlePlugin;

import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLStorageProvider extends SQLStorageProvider{

    public MySQLStorageProvider(CastlePlugin p) {
        super(p);

    }

    @Override
    void connect() throws SQLException {
        String host = super.plugin.getConfig().getString("storage.settings.host");
        int port = super.plugin.getConfig().getInt("storage.settings.port");
        String database = super.plugin.getConfig().getString("storage.settings.database");
        String username = super.plugin.getConfig().getString("storage.settings.username");
        String password = super.plugin.getConfig().getString("storage.settings.password");
        boolean ssl = super.plugin.getConfig().getBoolean("storage.settings.ssl");
        if (!isConnected()){
            String url = "jdbc:mysql://" +
                    host + ":" + port + "/" +
                    database + "?useSSL=" + ssl;
            super.plugin.getLogger().warning("Try to connect to "+url);
            setConnection(DriverManager.getConnection(url,username,password));
        }
    }
}
