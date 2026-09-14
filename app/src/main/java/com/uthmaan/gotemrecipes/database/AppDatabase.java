package com.uthmaan.gotemrecipes.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.uthmaan.gotemrecipes.dao.PantryDao;
import com.uthmaan.gotemrecipes.dao.RecipeDao;
import com.uthmaan.gotemrecipes.model.PantryItem;
import com.uthmaan.gotemrecipes.dao.RecipeIngredientDao;
import com.uthmaan.gotemrecipes.model.Recipe;
import com.uthmaan.gotemrecipes.model.RecipeIngredient;

@Database(
        entities = {
                PantryItem.class,
                Recipe.class,
                RecipeIngredient.class
        },
        version = 2,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase pantryDatabaseInstance;
    public abstract PantryDao pantryDao();
    public abstract RecipeDao recipeDao();
    public abstract RecipeIngredientDao recipeIngredientDao();

    // this is here to create one database that will be shared for the entire app that we will be making.
    public static AppDatabase getDatabase(Context context){
        if (pantryDatabaseInstance == null ) {
            synchronized (AppDatabase.class) {
                if (pantryDatabaseInstance == null) {
                    pantryDatabaseInstance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "got_em_recipes_database"
                    )
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return pantryDatabaseInstance;
    }
}
