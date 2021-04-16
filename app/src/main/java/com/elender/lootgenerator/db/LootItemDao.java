package com.elender.lootgenerator.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

/**
 * Interfaz con todas las sentencias para recoger los datos de la base de datos
 * */

@Dao
public interface LootItemDao {
    /*Insertar un elemento */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItem(LootItem... lootItems);

    //Mostrar los elementos de un determinado origen
    @Query("SELECT * FROM LootItem WHERE source = :source")
    LootItem[] showSourceItems(String source);

    //seleccionar todos los origenes DISTINTOS de la base de datos
    @Query("SELECT DISTINCT source from LootItem ")
    List<String> getSources();

    //Mostrar los elementos de un determinado origen
    @Query("SELECT * FROM LootItem WHERE source = :source AND loot_colour= :loot_colour ORDER BY RANDOM() LIMIT :quantity")
    LootItem[] getLoot(String source, String loot_colour, int quantity);


    @Query("SELECT count(*) FROM LootItem WHERE source = :source")
    int getSourceCount(String source);

    //sentencia que verifica si ya hay un elemento existente con el nombre indicado
    @Query("SELECT count(*) FROM LootItem WHERE name= :name")
    int checkItem( String name);


}
