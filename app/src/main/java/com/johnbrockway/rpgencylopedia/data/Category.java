package com.johnbrockway.rpgencylopedia.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "categories")
public class Category implements Item {

    public Category(int world, String listLabel, int icon, String singularLabel) {
        this.world = world;
        this.listLabel = listLabel;
        this.icon = icon;
        this.singularLabel = singularLabel;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    /**
     * The {@link com.johnbrockway.rpgencylopedia.data.World#id} that this category belongs to
     */
    @ColumnInfo(name = "world")
    public int world;

    @ColumnInfo(name = "list_label")
    public String listLabel;

    @ColumnInfo(name = "icon")
    public int icon;

    @ColumnInfo(name = "singular_label")
    public String singularLabel;

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
        return listLabel;
    }

    @Override
    public ItemType getItemType() {
        return ItemType.CATEGORY;
    }
}
