package com.elender.lootgenerator.dbmanagement;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {LootItem.class}, version = 1)
public abstract class ItemDB extends RoomDatabase {

    public abstract LootItemDao dao();

    public static ItemDB getItemDataBase(Context context){
        Builder<ItemDB> bd = Room.databaseBuilder(context, ItemDB.class, "LootDB");
        bd.allowMainThreadQueries();
        return bd.build();
    }
}
