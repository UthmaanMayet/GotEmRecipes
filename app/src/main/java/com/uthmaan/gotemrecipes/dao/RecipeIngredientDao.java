package com.uthmaan.gotemrecipes.dao;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.uthmaan.gotemrecipes.model.RecipeIngredient;
import java.util.List;
// This is here to handle how the ingredients end up linking to each of the recipes
@Dao
public interface RecipeIngredientDao{
  @Insert
  void addRecipeIngredient(RecipeIngredient recipeIngredient);
  @Query("SELECT * FROM recipe_ingredients WHERE recipeId = :recipeId")
    List<RecipeIngredient> getIngredientsForRecipe(int recipeId);
  @Query("DELETE FROM recipe_ingredients WHERE recipeId = :recipeId")
    void deleteIngredientsForRecipe(int recipeId);
}


