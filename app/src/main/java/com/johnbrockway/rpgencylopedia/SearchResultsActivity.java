package com.johnbrockway.rpgencylopedia;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.johnbrockway.rpgencylopedia.data.Category;
import com.johnbrockway.rpgencylopedia.data.DataAccessObject;
import com.johnbrockway.rpgencylopedia.data.Database;
import com.johnbrockway.rpgencylopedia.data.Entry;
import com.johnbrockway.rpgencylopedia.data.Note;

import java.util.List;

public class SearchResultsActivity extends AppCompatActivity {
    private RecyclerView resultsRecyclerView;
    private SearchResultsAdapter resultsAdapter;
    private Database db;
    private DataAccessObject dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        resultsRecyclerView = findViewById(R.id.results_recycler_view);
        resultsAdapter = new SearchResultsAdapter(this);
        resultsRecyclerView.setAdapter(resultsAdapter);
        resultsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = Database.getDatabase(this);
        dao = db.dataAccessObject();

        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            dao.getAllCategoriesForText(query).observe(this, new Observer<List<Category>>() {
                @Override
                public void onChanged(List<Category> categories) {
                    resultsAdapter.addCategories(categories);
                }
            });

            dao.getAllEntriesForText(query).observe(this, new Observer<List<Entry>>() {
                @Override
                public void onChanged(List<Entry> entries) {
                    resultsAdapter.addEntries(entries);
                }
            });

            dao.getAllNotesForText(query).observe(this, new Observer<List<Note>>() {
                @Override
                public void onChanged(List<Note> notes) {
                    resultsAdapter.addNotes(notes);
                }
            });


        }
    }

}
