package com.uthmaan.gotemrecipes;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.uthmaan.gotemrecipes.database.AppDatabase;
import com.uthmaan.gotemrecipes.model.Recipe;
import com.uthmaan.gotemrecipes.model.RecipeIngredient;
import java.util.List;
public class RecipeDetailActivity extends AppCompatActivity{
    private TextView recipeNameText;
    private TextView recipeCategoryText;
    private TextView recipeDescriptionText;
    private TextView recipeIngredientsText;
    private TextView recipeInstructionsText;
    private Button backButton;
    private AppDatabase appDatabase;
    private int recipeId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);
        recipeNameText =
                findViewById(R.id.textRecipeDetailName);
        recipeCategoryText =
                findViewById(R.id.textRecipeDetailCategory);
        recipeDescriptionText =
                findViewById(R.id.textRecipeDetailDescription);
        recipeIngredientsText =
                findViewById(R.id.textRecipeIngredients);
        recipeInstructionsText =
                findViewById(R.id.textRecipeInstructions);
        backButton =
                findViewById(R.id.buttonBack);
        backButton.setOnClickListener(view ->finish());
        appDatabase =AppDatabase.getDatabase(this);
        recipeId =
                getIntent().getIntExtra(
                        "RECIPE_ID",
                        -1
                );
                if (recipeId != -1) {
                    loadRecipeDetails();
                }
    }
    private void loadRecipeDetails() {
        new Thread(() -> {
            Recipe recipe =
                    appDatabase.recipeDao()
                            .getRecipeById(recipeId);
            List<RecipeIngredient> ingredients =
                    appDatabase.recipeIngredientDao()
                            .getIngredientsForRecipe(recipeId);
            StringBuilder ingredientList =
                    new StringBuilder();
            for (RecipeIngredient ingredient :ingredients) {
                ingredientList
                        .append(". ")
                        .append(ingredient.getIngredientName())
                        .append(" - ")
                        .append(ingredient.getRequiredQuantity())
                        .append(" ")
                        .append(ingredient.getMeasurementUnit())
                        .append("\n");
            }
            runOnUiThread(() -> {
                if (recipe != null) {
                    recipeNameText.setText(
                            recipe.getRecipeName()
                    );
                    recipeCategoryText.setText(
                            recipe.getRecipeCategory()
                    );
                    recipeDescriptionText.setText(
                            recipe.getRecipeDescription()
                    );
                    recipeIngredientsText.setText(
                            ingredientList.toString()
                    );
                    recipeInstructionsText.setText(
                            recipe.getCookingInstructions()
                    );
                }
            });
        }).start();
    }
}
