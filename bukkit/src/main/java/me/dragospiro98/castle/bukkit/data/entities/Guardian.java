package me.dragospiro98.castle.bukkit.data.entities;

public class Guardian implements CustomEntity{

    private String name;

    @Override
    public int getLife() {
        return 0;
    }

    @Override
    public void kill() {

    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
