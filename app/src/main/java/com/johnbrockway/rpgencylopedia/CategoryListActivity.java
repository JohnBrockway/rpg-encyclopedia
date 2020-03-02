package com.johnbrockway.rpgencylopedia;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.johnbrockway.rpgencylopedia.data.Category;
import com.johnbrockway.rpgencylopedia.data.DataAccessObject;
import com.johnbrockway.rpgencylopedia.data.Database;
import com.johnbrockway.rpgencylopedia.data.Entry;
import com.johnbrockway.rpgencylopedia.data.Note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
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
                Log.d("johnbrockway", Integer.toString(categories.size()));
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addTestData() {
        this.deleteDatabase("rpg_encyclopedia");

        Category category1 = new Category(0, "tests", "img", "test");
        Category category2 = new Category(0, "tests 2", "img", "test 2");List<Integer> links1 = new ArrayList<>();
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
        Entry entry1 = new Entry(0, 1, "entry 1", links1, links1);
        Entry entry2 = new Entry(0, 1, "entry 2", links2, links2);
        Entry entry3 = new Entry(0, 2, "entry 3", links3, links3);
        Entry entry4 = new Entry(0, 2, "entry 4", links4, links4);
        Note note1 = new Note(0, "note 1");
        Note note2 = new Note(0, "note 2");
        Note note3 = new Note(0, "note 3");
        Note note4 = new Note(0, "note 4");

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
