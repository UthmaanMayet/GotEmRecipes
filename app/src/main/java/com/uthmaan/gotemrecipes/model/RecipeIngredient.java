package com.uthmaan.gotemrecipes.model;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
// This is here to link each of th recipes to the overall ingredients and the quantities that are required
@Entity(tableName = "recipe_ingredients")
public class RecipeIngredient {
    @PrimaryKey(autoGenerate = true)
    private int recipeIngredientId;
    private int recipeId;
    private String ingredientName;
    private double requiredQuantity;
    private String measurementUnit;
    public RecipeIngredient(int recipeId,
                            String ingredientName,
                            double requiredQuantity,
                            String measurementUnit) {
        this.recipeId = recipeId;
        this.ingredientName = ingredientName;
        this.requiredQuantity = requiredQuantity;
        this.measurementUnit = measurementUnit;
    }

    public int getRecipeIngredientId() {
        return recipeIngredientId;
    }

    public void setRecipeIngredientId(int recipeIngredientId) {
        this.recipeIngredientId = recipeIngredientId;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public double getRequiredQuantity() {
        return requiredQuantity;
    }

    public void setRequiredQuantity(double requiredQuantity) {
        this.requiredQuantity = requiredQuantity;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }
}
