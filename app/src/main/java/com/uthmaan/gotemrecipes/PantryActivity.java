package com.uthmaan.gotemrecipes;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.uthmaan.gotemrecipes.data.RecipeSeeder;
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
        pantryAdapter = new PantryAdapter(pantryItem -> {
            // this is here ti open and add and edit the screen with the selected pantry
            Intent editIngredientScreen =
                    new Intent(PantryActivity.this,AddEditIngredientActivity.class);
            editIngredientScreen.putExtra(
                    "PANTRY_ITEM_ID",
                    pantryItem.getPantryItemId()
            );
            editIngredientScreen.putExtra(
                    "INGREDIENT_NAME",
                    pantryItem.getIngredientName()
            );
            editIngredientScreen.putExtra(
                    "PANTRY_QUANTITY",
                    pantryItem.getPantryQuantity()
            );
            editIngredientScreen.putExtra(
                    "MEASUREMENT_UNIT",
                    pantryItem.getMeasurementUnit()
            );
            editIngredientScreen.putExtra(
                    "INGREDIENT_CATEGORY",
                    pantryItem.getIngredientCategory()
            );
            editIngredientScreen.putExtra(
                    "EXPIRY_DATE",
                    pantryItem.getExpiryDate()
            );
            startActivity(editIngredientScreen);
        });
        pantryRecyclerView.setAdapter(pantryAdapter);
        // This will allow access to the rooms databases
        appDatabase = AppDatabase.getDatabase(this);
        new Thread(() -> RecipeSeeder.seedRecipes(appDatabase)).start();
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
                int itemCount = pantryItems.size();
                String pantrySummary;
                if (itemCount ==1) {
                    pantrySummary = "1 item" ;
                } else {
                    pantrySummary = itemCount + "items";
                }
                pantrySummaryText.setText(pantrySummary);
            });
        }).start();
    }

}