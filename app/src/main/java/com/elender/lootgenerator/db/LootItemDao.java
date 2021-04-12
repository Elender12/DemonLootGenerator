package com.elender.lootgenerator.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LootItemDao {
    /*  Insertar un elemento */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItem(LootItem... lootItems);

    //Mostrar los elementos de una determinada fuente
    @Query("SELECT * FROM LootItem WHERE source = :source")
    LootItem[] showSourceItems(String source);

    @Query("SELECT DISTINCT source from LootItem ")
    List<String> getSources();

    //Mostrar los elementos de una determinada fuente
    @Query("SELECT * FROM LootItem WHERE source = :source AND loot_colour= :loot_colour ORDER BY RANDOM() LIMIT :quantity")
    LootItem[] getLoot(String source, String loot_colour, int quantity);

    @Query("SELECT count(*) FROM LootItem WHERE source = :source")
    int getSourceCount(String source);

}
