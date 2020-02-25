package com.johnbrockway.myapplication.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "entries")
public class Entry {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    /**
     * The {@link com.johnbrockway.myapplication.data.World#id} that this category belongs to
     */
    @ColumnInfo(name = "world")
    public int world;

    /**
     * The {@link com.johnbrockway.myapplication.data.Category#id} that this belongs to
     */
    @ColumnInfo(name = "category")
    public int category;

    @ColumnInfo(name = "name")
    public String name;

    /* TODO Create a type converter for lists

    /**
     * List of {@link com.johnbrockway.myapplication.data.Entry#id} related to this entry
     *
    @ColumnInfo(name = "links")
    public int[] links;

    /**
     * List of {@link Note#id} related to this entry
     *
    @ColumnInfo(name = "notes")
    public int[] notes;

     */
}
