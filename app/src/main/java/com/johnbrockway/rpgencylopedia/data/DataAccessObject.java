package com.johnbrockway.rpgencylopedia.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

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

    @Query("SELECT * FROM worlds")
    LiveData<List<World>> getAllWorlds();

    @Query("SELECT * FROM categories WHERE world = :worldID")
    LiveData<List<Category>> getAllCategoriesForWorld(int worldID);

    @Query("SELECT * FROM entries WHERE category = :categoryID")
    LiveData<List<Entry>> getAllEntriesForCategory(int categoryID);

    @Query("SELECT * FROM notes WHERE id IN (:ids)")
    LiveData<List<Note>> getAllNotesForList(List<Integer> ids);
}
