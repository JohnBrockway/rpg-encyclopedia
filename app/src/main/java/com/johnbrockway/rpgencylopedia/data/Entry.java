package com.johnbrockway.rpgencylopedia.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import java.util.List;

@Entity(tableName = "entries")
public class Entry implements Item{

    public Entry(int world, int category, String name, List<Integer> links, int icon) {
        this.world = world;
        this.category = category;
        this.name = name;
        this.links = links;
        this.icon = icon;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    /**
     * The {@link com.johnbrockway.rpgencylopedia.data.World#id} that this category belongs to
     */
    @ColumnInfo(name = "world")
    public int world;

    /**
     * The {@link com.johnbrockway.rpgencylopedia.data.Category#id} that this belongs to
     */
    @ColumnInfo(name = "category")
    public int category;

    @ColumnInfo(name = "name")
    public String name;

    /**
     * List of {@link com.johnbrockway.rpgencylopedia.data.Entry#id} related to this entry
     */
    @ColumnInfo(name = "links")
    @TypeConverters({RPGTypeConverters.class})
    public List<Integer> links;

    @ColumnInfo(name = "icon")
    public int icon;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getIcon() {
        return icon;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public ItemType getItemType() {
        return ItemType.ENTRY;
    }
}
