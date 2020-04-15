package com.johnbrockway.rpgencylopedia.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note implements Item {

    public Note(int world, String text, int entryId) {
        this.world = world;
        this.text = text;
        this.entryId = entryId;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    /**
     * The {@link com.johnbrockway.rpgencylopedia.data.World#id} that this note belongs to
     */
    @ColumnInfo(name = "world")
    public int world;

    @ColumnInfo(name = "text")
    @NonNull
    public String text;

    /**
     * The {@link com.johnbrockway.rpgencylopedia.data.Entry#id} that this Note belongs to
     */
    @ColumnInfo(name = "entry_id")
    public int entryId;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getIcon() {
        return 0;
    }

    @Override
    public String getTitle() {
        return text;
    }

    @Override
    public ItemType getItemType() {
        return ItemType.NOTE;
    }
}
