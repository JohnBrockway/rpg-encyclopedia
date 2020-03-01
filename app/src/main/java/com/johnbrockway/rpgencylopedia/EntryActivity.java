package com.johnbrockway.rpgencylopedia;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import com.johnbrockway.rpgencylopedia.data.DataAccessObject;
import com.johnbrockway.rpgencylopedia.data.Database;
import com.johnbrockway.rpgencylopedia.data.Entry;
import com.johnbrockway.rpgencylopedia.data.Note;

import androidx.appcompat.app.AppCompatActivity;
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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

    private void populateRecyclerViews(Entry entry) {
        dao.getAllEntriesForListOfIDs(entry.links).observe(this, new Observer<List<Entry>>() {
            @Override
            public void onChanged(List<Entry> entries) {
                linksAdapter.setLinks(entries);
            }
        });

        dao.getAllNotesForListOfIDs(entry.notes).observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                notesAdapter.setNotes(notes);
            }
        });
    }
}
