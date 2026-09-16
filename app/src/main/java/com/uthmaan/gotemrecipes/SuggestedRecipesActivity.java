package com.uthmaan.gotemrecipes;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.uthmaan.gotemrecipes.adapter.RecipeAdapter;
import com.uthmaan.gotemrecipes.database.AppDatabase;
import com.uthmaan.gotemrecipes.model.PantryItem;
import com.uthmaan.gotemrecipes.model.Recipe;
import com.uthmaan.gotemrecipes.model.RecipeIngredient;
import com.uthmaan.gotemrecipes.utils.RecipeMatcher;
import java.util.ArrayList;
import java.util.List;
public class SuggestedRecipesActivity extends AppCompatActivity {
    private RecyclerView suggestedRecipesRecyclerView;
    private  TextView emptyRecipesText;
    private RecipeAdapter recipeAdapter;
    private AppDatabase appDatabase;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);
        suggestedRecipesRecyclerView =
                findViewById(R.id.recyclerSuggestedRecipes);
        emptyRecipesText =
                findViewById(R.id.textNoRecipes);
        backButton = findViewById(R.id.buttonSuggestedBack);
        suggestedRecipesRecyclerView.setLayoutManager(
                new LinearLayoutManager(this)
        );
        recipeAdapter = new RecipeAdapter(
                new ArrayList<>(),
                recipe -> {
                    Intent recipeDetailIntent =
                            new Intent(
                                    SuggestedRecipesActivity.this,
                                    RecipeDetailActivity.class
                            );
                    recipeDetailIntent.putExtra(
                            "RECIPE_ID" ,
                            recipe.getRecipeId()
                    );
                    startActivity(recipeDetailIntent);
                }
        );
        suggestedRecipesRecyclerView.setAdapter(recipeAdapter);
        backButton.setOnClickListener(view -> finish());
        appDatabase = AppDatabase.getDatabase(this);
        loadSuggestedRecipes();
    }
    private void loadSuggestedRecipes() {
        new Thread(() -> {
            List<PantryItem> pantryItems =
                    appDatabase.pantryDao().getAllPantryItems();
            List<Recipe> recipes =
                    appDatabase.recipeDao().getAllRecipes();
            runOnUiThread(() ->
            Toast.makeText(
                    SuggestedRecipesActivity.this,
                    "Recipes in database:" + recipes.size(),
                    Toast.LENGTH_LONG
            ).show()
            );
            List<RecipeIngredient> allRecipeIngredients =
                    new ArrayList<>();
            for (Recipe recipe : recipes) {
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
                recipeAdapter.setRecipes(matchingRecipes);
                if (matchingRecipes.isEmpty()) {
                    emptyRecipesText.setVisibility(View.VISIBLE);
                    suggestedRecipesRecyclerView.setVisibility(View.GONE);
                }else {
                    emptyRecipesText.setVisibility(View.GONE);
                    suggestedRecipesRecyclerView.setVisibility(View.VISIBLE);
                }
            });
        }).start();
    }
}
