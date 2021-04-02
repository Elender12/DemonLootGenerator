package com.elender.lootgenerator.dbmanagement;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface LootItemDao {
    /*  Insertar un elemento */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertItem(LootItem... lootItems);

    //Mostrar los elementos de una determinada fuente
    @Query("SELECT * FROM items WHERE source = :source")
    LootItem[] showSourceItems(String source);
}
