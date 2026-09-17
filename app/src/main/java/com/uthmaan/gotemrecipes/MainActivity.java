package com.uthmaan.gotemrecipes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.uthmaan.gotemrecipes.database.AppDatabase;
import com.uthmaan.gotemrecipes.model.PantryItem;
import com.uthmaan.gotemrecipes.model.Recipe;
import com.uthmaan.gotemrecipes.model.RecipeIngredient;
import com.uthmaan.gotemrecipes.utils.RecipeMatcher;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView pantryCountText;
    private TextView homeSuggestionText;
    private Button pantryButton;
    private Button recipesButton;
    private Button settingsButton;
    private AppDatabase appDatabase;
    private Button allRecipesButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pantryCountText =
                findViewById(R.id.textHomePantryCount);
        homeSuggestionText =
                findViewById(R.id.textHomeSuggestion);
        pantryButton =
                findViewById(R.id.buttonHomePantry);
        recipesButton =
                findViewById(R.id.buttonHomeRecipes);
        allRecipesButton =
                findViewById(R.id.buttonHomeAllRecipes);
        settingsButton =
                findViewById(R.id.buttonHomeSettings);
        appDatabase =
                AppDatabase.getDatabase(this);

        allRecipesButton.setOnClickListener(view -> {
            Intent allRecipesIntent =
                    new Intent(MainActivity.this,
                            AllRecipesActivity.class
                    );
            startActivity(allRecipesIntent);
        });

        pantryButton.setOnClickListener(view -> {
            Intent pantryIntent =
                    new Intent(
                            MainActivity.this,
                            PantryActivity.class
                    );
            startActivity(pantryIntent);
        });
        recipesButton.setOnClickListener(view -> {
            Intent recipesIntent =
                    new Intent(
                            MainActivity.this,
                            SuggestedRecipesActivity.class
                    );
            startActivity(recipesIntent);
        });
            settingsButton.setOnClickListener(view -> {
                Intent settingsIntent =
                        new Intent(
                                MainActivity.this,
                                SettingsActivity.class
                        );
                startActivity(settingsIntent);
            });
            loadHomeData();
        }
        @Override
        protected void onResume() {
            super.onResume();
            if (appDatabase != null) {
                loadHomeData();
            }
        }
        private void loadHomeData() {
            new Thread(() -> {
                List<PantryItem> pantryItems =
                        appDatabase.pantryDao()
                                .getAllPantryItems();
                int pantryCount =
                        pantryItems.size();
                List<Recipe> recipes =
                        appDatabase.recipeDao()
                                .getAllRecipes();
                List<RecipeIngredient> allRecipeIngredients =
                        new ArrayList<>();
                for (Recipe recipe :recipes) {
                    List<RecipeIngredient> recipeIngredients =
                            appDatabase.recipeIngredientDao()
                                    .getIngredientsForRecipe(
                                            recipe.getRecipeId()
                                    );
                    allRecipeIngredients.addAll(recipeIngredients);
                }
                List<Recipe> matchingRecipes =
                        RecipeMatcher.findMatchingRecipes(
                                recipes,
                                pantryItems,
                                allRecipeIngredients
                        );
                runOnUiThread(() -> {
                    if (pantryCount ==1) {
                        pantryCountText.setText(
                                "1 pantry item"
                        );
                    } else {
                        pantryCountText.setText(
                                pantryCount + " pantry items"
                        );
                    }
                    if (matchingRecipes.isEmpty()) {
                        homeSuggestionText.setText(
                                "Add a few more ingredients to unlock other recipes"
                        );
                    } else {
                        Recipe suggestedRecipe =
                                matchingRecipes.get(0);
                        homeSuggestionText.setText(
                                suggestedRecipe.getRecipeName()
                        );
                    }
                });
            }).start();
    }

}

