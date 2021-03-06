package com.johnbrockway.rpgencylopedia;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.johnbrockway.rpgencylopedia.data.DataAccessObject;
import com.johnbrockway.rpgencylopedia.data.Database;
import com.johnbrockway.rpgencylopedia.data.Entry;

import java.util.List;

public class EntryListActivity extends AppCompatActivity {
    private RecyclerView entriesRecyclerView;
    private EntriesAdapter entriesAdapter;
    private Database db;
    private DataAccessObject dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int categoryID = getIntent().getIntExtra(
                getString(R.string.intent_category_id), -1);
        String categoryName = getIntent().getStringExtra(getString(R.string.intent_category_name));
        int categoryIcon = getIntent().getIntExtra(getString(R.string.intent_category_icon), R.drawable.ic_storage_white_24dp);

        toolbar.setTitle(categoryName);
        toolbar.setLogo(categoryIcon);

        entriesRecyclerView = findViewById(R.id.entries_recycler_view);
        entriesAdapter = new EntriesAdapter(this);
        entriesRecyclerView.setAdapter(entriesAdapter);
        entriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = Database.getDatabase(this);
        dao = db.dataAccessObject();
        dao.getAllEntriesForCategory(categoryID).observe(this, new Observer<List<Entry>>() {
            @Override
            public void onChanged(List<Entry> entries) {
                entriesAdapter.setEntries(entries);
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
}
