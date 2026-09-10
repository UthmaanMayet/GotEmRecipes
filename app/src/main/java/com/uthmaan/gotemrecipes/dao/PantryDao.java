package com.uthmaan.gotemrecipes.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.uthmaan.gotemrecipes.model.PantryItem;
import java.util.List;

@Dao
public interface PantryDao {
    @Insert
    void addPantryItem(PantryItem pantryItem);
    @Update
    void updatePantryItem(PantryItem pantryItem);
    @Delete
    void deletePantryItem(PantryItem pantryItem);
    @Query("SELECT * FROM pantry_items ORDER BY ingredientName ASC")
    List<PantryItem> getAllPantryItems();
    @Query("SELECT * FROM pantry_items WHERE pantryItemId = :itemId LIMIT 1")
    PantryItem getPantryItemById(int itemId);
}
