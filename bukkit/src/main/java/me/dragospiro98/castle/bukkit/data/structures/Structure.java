package me.dragospiro98.castle.bukkit.data.structures;


import me.dragospiro98.castle.bukkit.data.Clan;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * Interfaccia per le strutture
 * - Castelli
 * - Torri
 */
public interface Structure {
    /**
     * @return l'id della struttura
     */
    UUID getUuid();

    /**
     * @return l'id del clan di appartenenza, potrebbe essere null quando si tratta delle Torri
     */
    @Nullable
    UUID getClan();
}
