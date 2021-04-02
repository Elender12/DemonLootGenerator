package com.elender.lootgenerator.dbmanagement;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "items")
public class LootItem {

    @PrimaryKey
    @NonNull
    public String id;

    @ColumnInfo(name="source")
    public String source;

    @ColumnInfo(name="loot_colour")
    public String lootColour;

    @ColumnInfo(name="name")
    public String name;

    public LootItem() {
        this.id= UUID.randomUUID().toString();
    }

    public LootItem(String source, String lootColour, String name) {
        this.id= UUID.randomUUID().toString();
        this.source = source;
        this.lootColour = lootColour;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLootColour() {
        return lootColour;
    }

    public void setLootColour(String lootColour) {
        this.lootColour = lootColour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}