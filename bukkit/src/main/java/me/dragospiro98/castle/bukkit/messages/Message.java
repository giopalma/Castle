package me.dragospiro98.castle.bukkit.messages;

public enum Message {
    FULL_INVENTORY,
    NOT_IN_CLAN,
    NOT_LEADER;

     private String path;

    Message(){
        this.path = "messages."+this.name().toLowerCase();
    }

    public String getPath() {
        return path;
    }
}
