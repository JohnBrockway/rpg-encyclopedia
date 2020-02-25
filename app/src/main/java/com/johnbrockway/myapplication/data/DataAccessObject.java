package com.johnbrockway.myapplication.data;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface DataAccessObject {

    @Insert
    int insertWorld(World world);

    @Insert
    int insertCategory(Category category);

    @Insert
    int insertEntry(Entry entry);

    @Insert
    int insertNote(Note note);
}
