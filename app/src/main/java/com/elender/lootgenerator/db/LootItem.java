package com.elender.lootgenerator.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Entity(tableName = "LootItem")

public class LootItem {

    @PrimaryKey
    @NonNull
    public String id;

    @ColumnInfo(name="source")
    @NonNull
    public String source;

    @ColumnInfo(name="loot_colour")
    @NonNull
    public String lootColour;

    @ColumnInfo(name="name")
    @NonNull
    public String name;

    public LootItem(@NotNull String source, @NotNull String lootColour, @NotNull String name) {
        this.id= UUID.randomUUID().toString();
        this.source = source;
        this.lootColour = lootColour;
        this.name = name;
    }

    public @NotNull String getId() {
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
