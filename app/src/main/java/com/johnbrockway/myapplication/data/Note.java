package com.johnbrockway.myapplication.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class Note {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    /**
     * The {@link com.johnbrockway.myapplication.data.World#id} that this category belongs to
     */
    @ColumnInfo(name = "world")
    public int world;

    @ColumnInfo(name = "text")
    @NonNull
    public String text;
}
