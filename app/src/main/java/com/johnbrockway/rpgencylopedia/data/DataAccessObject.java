package com.johnbrockway.rpgencylopedia.data;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface DataAccessObject {

    @Insert
    void insertWorld(World world);

    @Insert
    void insertCategory(Category category);

    @Insert
    void insertEntry(Entry entry);

    @Insert
    void insertNote(Note note);
}
