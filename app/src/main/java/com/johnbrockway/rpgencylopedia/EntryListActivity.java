package com.johnbrockway.rpgencylopedia;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.johnbrockway.rpgencylopedia.data.DataAccessObject;
import com.johnbrockway.rpgencylopedia.data.Database;
import com.johnbrockway.rpgencylopedia.data.Entry;

import java.util.ArrayList;
import java.util.List;

public class EntryListActivity extends AppCompatActivity {
    private RecyclerView entriesRecyclerView;
    private EntriesAdapter entriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int categoryID = getIntent().getIntExtra(
                getString(R.string.intent_category_id), -1);

        entriesRecyclerView = findViewById(R.id.entries_recycler_view);
        entriesAdapter = new EntriesAdapter(this);
        entriesRecyclerView.setAdapter(entriesAdapter);
        entriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Entry entry1 = new Entry(0, 1, "entry 1", null, null);
        Entry entry2 = new Entry(0, 1, "entry 2", null, null);
        Entry entry3 = new Entry(0, 2, "entry 3", null, null);
        Entry entry4 = new Entry(0, 2, "entry 4", null, null);
        Database db = Database.getDatabase(this);
        DataAccessObject dao = db.dataAccessObject();
        dao.getAllEntriesForCategory(categoryID).observe(this, new Observer<List<Entry>>() {
            @Override
            public void onChanged(List<Entry> entries) {
                entriesAdapter.setEntries(entries);
            }
        });

        Database.databaseWriteExecutor.execute(() -> {
            dao.insertEntry(entry1);
            dao.insertEntry(entry2);
            dao.insertEntry(entry3);
            dao.insertEntry(entry4);
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
}
