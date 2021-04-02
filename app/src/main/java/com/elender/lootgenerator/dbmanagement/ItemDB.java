package com.elender.lootgenerator.dbmanagement;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {LootItem.class}, version = 2, exportSchema=false)
public abstract class ItemDB extends RoomDatabase {

    public abstract LootItemDao dao();

    static Migration migration = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
           // database.execSQL("ALTER TABLE 'LootItem'");
        }
    };

    public static ItemDB getItemDataBase(Context context){

        Builder<ItemDB> bd = Room.databaseBuilder(context, ItemDB.class, "items.db").createFromAsset("database/items.db").addMigrations(migration);
        bd.allowMainThreadQueries();
        return bd.build();
    }
}
