package com.johnbrockway.myapplication.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "worlds")
public class World {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
}
