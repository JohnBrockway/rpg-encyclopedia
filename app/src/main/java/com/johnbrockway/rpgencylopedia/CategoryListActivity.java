package com.johnbrockway.rpgencylopedia;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.johnbrockway.rpgencylopedia.data.DataAccessObject;
import com.johnbrockway.rpgencylopedia.data.Database;
import com.johnbrockway.rpgencylopedia.data.Note;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class CategoryListActivity extends AppCompatActivity {
    private RecyclerView categoriesRecyclerView;
    private CategoriesAdapter categoriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Note note = new Note();
        note.text = "test";
        note.world = 0;
        Database db = Database.getDatabase(this);
        DataAccessObject dao = db.dataAccessObject();
        Database.databaseWriteExecutor.execute(() -> {
            dao.insertNote(note);
        });

        categoriesRecyclerView = findViewById(R.id.categories_recycler_view);
        categoriesAdapter = new CategoriesAdapter(this);
        categoriesRecyclerView.setAdapter(categoriesAdapter);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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
