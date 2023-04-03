package me.dragospiro98.castle.bukkit.data.storage;

import me.dragospiro98.castle.bukkit.CastlePlugin;
import me.dragospiro98.castle.bukkit.data.User;

import java.sql.SQLException;
import java.util.UUID;

public interface StorageProvider {

    User getPlayerData(UUID id) throws SQLException;


    /**
     * Questa funzione andrà a creare la tabella castle_users se non è già presente nel database
     */
    void createUserTable();

    void init();

    /**
     * Permette di aggiornare il database, aggiungendo le modifiche presenti nella cache (PlayerManager)
     */
    void close();

    void updateUser(UUID uuid, User user);
}
