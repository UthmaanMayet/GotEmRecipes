package com.uthmaan.gotemrecipes.model;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pantry_items")
public class PantryItem {
    @PrimaryKey(autoGenerate = true)
    private int pantryItemId;
    private String ingredientName;
    private double pantryQuantity;
    private String measurementUnit;
    private String ingredientCategory;
    private String expiryDate;

    // This is where room uses the following constructors when the new pantry items are created
    public PantryItem(String ingredientName,
                      double pantryQuantity,
                      String measurementUnit,
                      String ingredientCategory,
                      String expiryDate) {
        this.ingredientName = ingredientName;
        this.pantryQuantity = pantryQuantity;
        this.measurementUnit = measurementUnit;
        this.ingredientCategory = ingredientCategory;
        this.expiryDate = expiryDate;
    }

    public int getPantryItemId() {
        return pantryItemId;
    }

    public void setPantryItemId(int pantryItemId) {
        this.pantryItemId = pantryItemId;
    }

    public String getIngredientName() {
        return ingredientName;
    }
    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
    public double getPantryQuantity() {
        return pantryQuantity;
    }

    public void setPantryQuantity(double pantryQuantity) {
        this.pantryQuantity = pantryQuantity;
    }
    public String getMeasurementUnit() {
        return measurementUnit;
    }
    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }
    public String getIngredientCategory() {
        return ingredientCategory;
    }

    public void setIngredientCategory(String ingredientCategory) {
        this.ingredientCategory = ingredientCategory;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
