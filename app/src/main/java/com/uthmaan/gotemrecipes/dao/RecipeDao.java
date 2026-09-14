package com.uthmaan.gotemrecipes.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import  com.uthmaan.gotemrecipes.model.Recipe;
import java.util.List;
@Dao
public interface RecipeDao {
    @Insert
    long addRecipe(Recipe recipe);
    @Query("SELECT * FROM recipes ORDER BY recipeName ASC")
    List<Recipe> getAllRecipes();
    @Query("SELECT * FROM recipes WHERE recipeId = :recipeId LIMIT 1")
    Recipe getRecipeById(int recipeId);
    @Query("SELECT COUNT(*) FROM recipes")
    int getRecipeCount();
}
