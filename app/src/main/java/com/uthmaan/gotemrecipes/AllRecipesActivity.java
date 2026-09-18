package com.uthmaan.gotemrecipes;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.uthmaan.gotemrecipes.adapter.RecipeAdapter;
import com.uthmaan.gotemrecipes.database.AppDatabase;
import com.uthmaan.gotemrecipes.model.Recipe;
import java.util.ArrayList;
import java.util.List;

public class AllRecipesActivity extends AppCompatActivity{
    private RecyclerView allRecipesRecyclerView;
    private Button backButton;
    private RecipeAdapter recipeAdapter;
    private AppDatabase appDatabase;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_recipes);

        allRecipesRecyclerView =
                findViewById(R.id.recyclerAllRecipes);
        backButton =
                findViewById(R.id.buttonAllRecipesBack);
        allRecipesRecyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );
        bottomNavigationView =findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.navRecipes);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if(itemId == R.id.navHome) {
                startActivity(new Intent(AllRecipesActivity.this, MainActivity.class));
                return true;
            } else if (itemId == R.id.navPantry) {
                startActivity(new Intent(AllRecipesActivity.this, PantryActivity.class));
                return true;
            } else if (itemId ==R.id.navRecipes) {
                return true;
            } else if (itemId == R.id.navSettings) {
                startActivity(new Intent(AllRecipesActivity.this, SettingsActivity.class));
                return true;
            }
            return false;
        });
        recipeAdapter = new RecipeAdapter(
                new ArrayList<>(),
                recipe -> {
                    Intent recipeDetailIntent =
                            new Intent(
                                    AllRecipesActivity.this,
                                    RecipeDetailActivity.class
                            );
                    recipeDetailIntent.putExtra(
                            "RECIPE_ID" ,
                            recipe.getRecipeId()
                    );
                    startActivity(recipeDetailIntent);
                }
        );
        allRecipesRecyclerView.setAdapter(recipeAdapter);
        appDatabase =
                AppDatabase.getDatabase(this);
        backButton.setOnClickListener(view ->
                finish()
        );
        loadAllRecipes();
    }
    private void loadAllRecipes() {
        new Thread(() -> {
            List<Recipe> recipes =
                    appDatabase.recipeDao()
                            .getAllRecipes();
            runOnUiThread(() ->
                    recipeAdapter.setRecipes(recipes)
            );
        }).start();
    }
}
