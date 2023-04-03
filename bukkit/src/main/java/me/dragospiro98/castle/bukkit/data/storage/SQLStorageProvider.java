package me.dragospiro98.castle.bukkit.data.storage;

import me.dragospiro98.castle.bukkit.CastlePlugin;
import me.dragospiro98.castle.bukkit.data.User;
import me.dragospiro98.castle.bukkit.managers.PlayerManager;

import java.sql.*;
import java.util.UUID;

public abstract class SQLStorageProvider implements StorageProvider{

    private final String MAKE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS `castle_users` (`uuid` VARCHAR(36) PRIMARY KEY,`trophies` INT)";
    private final String GET_USER = "SELECT *  FROM `castle_users` WHERE `uuid` = ?";
    private final String ADD_USER = "INSERT INTO `castle_users` (`uuid`,`trophies`) VALUES (?,?) ON DUPLICATE KEY UPDATE `trophies` = ?";

    protected CastlePlugin plugin;
    private Connection connection;
    public SQLStorageProvider(CastlePlugin p) {
        plugin = p;
        init();
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User getPlayerData(UUID id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(GET_USER);
        ps.setString(1,id.toString());
        ResultSet resultSet = ps.executeQuery();
        if(resultSet.next()){
            int retrievedTrophies = resultSet.getInt("trophies");
            return new User(id,retrievedTrophies,plugin);
        }
        return null;
    }

    @Override
    public void init() {
        try{
            connect();
            plugin.getLogger().info("Connected to database");
        } catch (SQLException e){
            plugin.getLogger().warning(e.getMessage());
        }
    }

    boolean isConnected(){
        return connection != null;
    }

    abstract void connect() throws SQLException;

    private void disconnect() throws SQLException {
        if(isConnected()){
            connection.close();
            connection = null;
        }
    }

    @Override
    public void createUserTable() {
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(MAKE_USERS_TABLE);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
       PlayerManager.getInstance().close(this);
       if(isConnected()){
           try {
               disconnect();
           }catch (SQLException e){
               plugin.getLogger().warning("Unable to disconnect from database");
           }
       }
    }

    @Override
    public void updateUser(UUID uuid, User user) {
        try {
            PreparedStatement ps = connection.prepareStatement(ADD_USER);
            ps.setString(1,uuid.toString());
            ps.setInt(2,user.getTrophies());
            ps.setInt(3,user.getTrophies());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
