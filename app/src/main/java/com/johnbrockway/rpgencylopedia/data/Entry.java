package com.johnbrockway.rpgencylopedia.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;
import java.util.List;

@Entity(tableName = "entries")
public class Entry {

    public Entry(int world, int category, String name, List<Integer> links, List<Integer> notes) {
        this.world = world;
        this.category = category;
        this.name = name;
        this.links = links;
        this.notes = notes;
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

    /**
     * List of {@link Note#id} related to this entry
     */
    @ColumnInfo(name = "notes")
    @TypeConverters({RPGTypeConverters.class})
    public List<Integer> notes;
}
