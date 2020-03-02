package com.johnbrockway.rpgencylopedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.johnbrockway.rpgencylopedia.data.Category;
import com.johnbrockway.rpgencylopedia.data.DataAccessObject;
import com.johnbrockway.rpgencylopedia.data.Database;

import java.util.ArrayList;
import java.util.List;

public class EditEntryActivity extends AppCompatActivity {
    private Database db;
    private DataAccessObject dao;
    Spinner categoriesSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = Database.getDatabase(this);
        dao = db.dataAccessObject();

        categoriesSpinner = findViewById(R.id.categories_spinner);

        dao.getAllCategoriesForWorld(0).observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(List<Category> categories) {
                populateSpinner(categories);
            }
        });
    }

    private void populateSpinner(List<Category> categories) {
        List<String> categoryNames = new ArrayList<>();
        for (Category category : categories) {
            categoryNames.add(category.singularLabel);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                categoryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoriesSpinner.setAdapter(adapter);
    }
}
