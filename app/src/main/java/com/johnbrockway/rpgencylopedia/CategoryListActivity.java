package com.johnbrockway.rpgencylopedia;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.johnbrockway.rpgencylopedia.data.Category;
import com.johnbrockway.rpgencylopedia.data.DataAccessObject;
import com.johnbrockway.rpgencylopedia.data.Database;
import com.johnbrockway.rpgencylopedia.data.Entry;
import com.johnbrockway.rpgencylopedia.data.Note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryListActivity extends AppCompatActivity {
    private RecyclerView categoriesRecyclerView;
    private CategoriesAdapter categoriesAdapter;
    private Database db;
    private DataAccessObject dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        categoriesRecyclerView = findViewById(R.id.categories_recycler_view);
        categoriesAdapter = new CategoriesAdapter(this);
        categoriesRecyclerView.setAdapter(categoriesAdapter);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = Database.getDatabase(this);
        dao = db.dataAccessObject();

        addTestData();

        dao.getAllCategoriesForWorld(0).observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                categoriesAdapter.setCategories(categories);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditEntryActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.toolbar_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(
                        new ComponentName(getApplicationContext(), SearchResultsActivity.class)));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.toolbar_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addTestData() {
        this.deleteDatabase("rpg_encyclopedia");

        Category category1 = new Category(0, "people", R.drawable.ic_person_white_24dp, "person");
        Category category2 = new Category(0, "places", R.drawable.ic_add_white_24dp, "place");
        List<Integer> links1 = new ArrayList<>();
        links1.add(2);
        links1.add(3);
        links1.add(4);
        List<Integer> links2 = new ArrayList<>();
        links2.add(1);
        links2.add(3);
        links2.add(4);
        List<Integer> links3 = new ArrayList<>();
        links3.add(1);
        links3.add(2);
        links3.add(4);
        List<Integer> links4 = new ArrayList<>();
        links4.add(1);
        links4.add(2);
        links4.add(3);
        Entry entry1 = new Entry(0, 1, "gerbo", links1, R.drawable.ic_person_white_24dp);
        Entry entry2 = new Entry(0, 1, "odo", links2, R.drawable.ic_person_white_24dp);
        Entry entry3 = new Entry(0, 2, "lys", links3, R.drawable.ic_add_white_24dp);
        Entry entry4 = new Entry(0, 2, "house of the serpent sin", links4, R.drawable.ic_add_white_24dp);
        Note note1 = new Note(0, "note 1", 1);
        Note note2 = new Note(0, "note 2", 2);
        Note note3 = new Note(0, "note 3", 3);
        Note note4 = new Note(0, "note 4", 4);

        Database.databaseWriteExecutor.execute(() -> {
            dao.insert(category1);
            dao.insert(category2);
            dao.insert(entry1);
            dao.insert(entry2);
            dao.insert(entry3);
            dao.insert(entry4);
            dao.insert(note1);
            dao.insert(note2);
            dao.insert(note3);
            dao.insert(note4);
        });
    }
}
