package com.johnbrockway.rpgencylopedia;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
import android.widget.TextView;

import java.util.List;

public class EntryActivity extends AppCompatActivity {
    private RecyclerView linksRecyclerView;
    private RecyclerView notesRecyclerView;
    private LinksAdapter linksAdapter;
    private NotesAdapter notesAdapter;
    private Database db;
    private DataAccessObject dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        linksRecyclerView = findViewById(R.id.links_recycler_view);
        linksAdapter = new LinksAdapter(this);
        linksRecyclerView.setAdapter(linksAdapter);
        linksRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        notesRecyclerView = findViewById(R.id.notes_recycler_view);
        notesAdapter = new NotesAdapter(this);
        notesRecyclerView.setAdapter(notesAdapter);
        notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        int entryID = getIntent().getIntExtra(
                getString(R.string.intent_entry_id), -1);
        String entryName = getIntent().getStringExtra(getString(R.string.intent_entry_name));
        int entryIcon = getIntent().getIntExtra(getString(R.string.intent_entry_icon), R.drawable.ic_storage_white_24dp);

        toolbar.setTitle(entryName);
        toolbar.setLogo(entryIcon);

        db = Database.getDatabase(this);
        dao = db.dataAccessObject();

        dao.getEntryByID(entryID).observe(this, new Observer<Entry>() {
            @Override
            public void onChanged(Entry entry) {
                TextView textView = findViewById(R.id.entry_name);
                textView.setText(entry.name);

                populateRecyclerViews(entry);
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

    private void populateRecyclerViews(Entry entry) {
        dao.getAllEntriesForListOfIDs(entry.links).observe(this, new Observer<List<Entry>>() {
            @Override
            public void onChanged(List<Entry> entries) {
                linksAdapter.setLinks(entries);
            }
        });

        dao.getAllNotesForEntry(entry.id).observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                notesAdapter.setNotes(notes);
            }
        });
    }
}
