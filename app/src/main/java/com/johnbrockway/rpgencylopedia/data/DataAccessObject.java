package com.johnbrockway.rpgencylopedia.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DataAccessObject {

    @Insert
    void insert(World world);

    @Insert
    void insert(Category category);

    @Insert
    void insert(Entry entry);

    @Insert
    void insert(Note note);

    @Query("SELECT * FROM entries WHERE id = :entryID LIMIT 1")
    LiveData<Entry> getEntryByID(int entryID);

    @Query("SELECT * FROM entries WHERE id IN (:ids)")
    LiveData<List<Entry>> getAllEntriesForListOfIDs(List<Integer> ids);

    @Query("SELECT * FROM worlds")
    LiveData<List<World>> getAllWorlds();

    @Query("SELECT * FROM categories WHERE world = :worldID")
    LiveData<List<Category>> getAllCategoriesForWorld(int worldID);

    @Query("SELECT * FROM entries WHERE category = :categoryID")
    LiveData<List<Entry>> getAllEntriesForCategory(int categoryID);

    @Query("SELECT * FROM notes WHERE id IN (:ids)")
    LiveData<List<Note>> getAllNotesForListOfIDs(List<Integer> ids);

    @Query("SELECT * FROM categories WHERE list_label LIKE '%'||:text||'%'")
    LiveData<List<Category>> getAllCategoriesForText(String text);

    @Query("SELECT * FROM entries WHERE name LIKE '%'||:text||'%'")
    LiveData<List<Entry>> getAllEntriesForText(String text);

    @Query("SELECT * FROM notes WHERE text LIKE '%'||:text||'%'")
    LiveData<List<Note>> getAllNotesForText(String text);
}
