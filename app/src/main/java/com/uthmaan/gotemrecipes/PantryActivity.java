package com.uthmaan.gotemrecipes;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uthmaan.gotemrecipes.adapter.PantryAdapter;
import com.uthmaan.gotemrecipes.database.AppDatabase;
import com.uthmaan.gotemrecipes.model.PantryItem;
import java.util.List;
import android.content.Intent;

public class PantryActivity extends AppCompatActivity{
    private TextView pantrySummaryText;
    private RecyclerView pantryRecyclerView;
    private Button addIngredientButton;
    private PantryAdapter pantryAdapter;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry);
        // this is here to link the java variables to the views created in the pantry
        pantrySummaryText = findViewById(R.id.textPantrySummary);
        pantryRecyclerView = findViewById(R.id.recyclerPantryItems);
        addIngredientButton = findViewById(R.id.buttonAddIngredient);
        // This is here to open the form where the user can then add a new ingredient to pantry
        addIngredientButton.setOnClickListener(view -> {
            Intent openAddIngredientScreen =
                    new Intent(PantryActivity.this, AddEditIngredientActivity.class);
            startActivity(openAddIngredientScreen);
        });
        // this will be here to display the pantry items in the list
        pantryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pantryAdapter = new PantryAdapter();
        pantryRecyclerView.setAdapter(pantryAdapter);
        // This will allow access to the rooms databases
        appDatabase = AppDatabase.getDatabase(this);
        loadPantryItems();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // this will be there to refresh the pantry whenever the user returns to the screen
        if(appDatabase != null && pantryAdapter != null ) {
            loadPantryItems();
        }
    }
    private void loadPantryItems() {
        //This is where the room database operations are done
        new Thread(() -> {
            List<PantryItem> pantryItems =
                    appDatabase.pantryDao().getAllPantryItems();
            runOnUiThread(() -> {
                pantryAdapter.setPantryItems(pantryItems);
                String pantrySummary = pantryItems.size() + "items";
                pantrySummaryText.setText(pantrySummary);
            });
        }).start();
    }

}