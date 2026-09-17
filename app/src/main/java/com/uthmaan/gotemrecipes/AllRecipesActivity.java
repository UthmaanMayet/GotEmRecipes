package com.uthmaan.gotemrecipes;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
