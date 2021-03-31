package com.elender.lootgenerator.dbmanagement;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity
public class LootItem {

    @PrimaryKey
    private UUID id;

    @ColumnInfo(name="source")
    private String source;

    @ColumnInfo(name="loot_colour")
    private String lootColour;

    @ColumnInfo(name="name")
    private String name;

    public LootItem() {
        this.id= UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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
