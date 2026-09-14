package com.uthmaan.gotemrecipes.model;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipes")
// this is essentially the main deatils for each of the recipes available on the app
public class Recipe {
    @PrimaryKey(autoGenerate = true)
    private int recipeId;
    private String recipeName;
    private String recipeDescription;
    private String recipeCategory;
    private String cookingInstructions;
    public Recipe(String recipeName,
                  String recipeDescription,
                  String recipeCategory,
                  String cookingInstructions){
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.recipeCategory = recipeCategory;
        this.cookingInstructions = cookingInstructions;
    }
    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
    public  String getRecipeName(){
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
    public String getRecipeDescription() {
        return recipeDescription;
    }
    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }
    public String getRecipeCategory() {
        return recipeCategory;
    }

    public void setRecipeCategory(String recipeCategory) {
        this.recipeCategory = recipeCategory;
    }
    public String getCookingInstructions () {
        return cookingInstructions;
    }

    public void setCookingInstructions(String cookingInstructions) {
        this.cookingInstructions = cookingInstructions;
    }
}
