package com.johnbrockway.rpgencylopedia.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {

    public Note(int world, String text) {
        this.world = world;
        this.text = text;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    /**
     * The {@link com.johnbrockway.rpgencylopedia.data.World#id} that this category belongs to
     */
    @ColumnInfo(name = "world")
    public int world;

    @ColumnInfo(name = "text")
    @NonNull
    public String text;
}
