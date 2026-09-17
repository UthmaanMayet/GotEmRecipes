package com.uthmaan.gotemrecipes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.uthmaan.gotemrecipes.database.AppDatabase;

public class MainActivity extends AppCompatActivity {
    private TextView pantryCountText;
    private TextView homeSuggestionText;
    private Button pantryButton;
    private Button recipesButton;
    private Button settingsButton;
    private AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pantryCountText =
                findViewById(R.id.textHomePantryCount);
        homeSuggestionText =
                findViewById(R.id.textHomeSuggestion);
        pantryButton =
                findViewById(R.id.buttonHomePantry);
        recipesButton =
                findViewById(R.id.buttonHomeRecipes);
        settingsButton =
                findViewById(R.id.buttonHomeSettings);
        appDatabase =
                AppDatabase.getDatabase(this);

        pantryButton.setOnClickListener(view -> {
            Intent pantryIntent =
                    new Intent(
                            MainActivity.this,
                            PantryActivity.class
                    );
            startActivity(pantryIntent);
        });
        recipesButton.setOnClickListener(view -> {
            Intent recipesIntent =
                    new Intent(
                            MainActivity.this,
                            SuggestedRecipesActivity.class
                    );
            startActivity(recipesIntent);
        });
            settingsButton.setOnClickListener(view -> {
                Intent settingsIntent =
                        new Intent(
                                MainActivity.this,
                                SettingsActivity.class
                        );
                startActivity(settingsIntent);
            });
            loadHomeData();
        }
        @Override
        protected void onResume() {
            super.onResume();
            if (appDatabase != null) {
                loadHomeData();
            }
        }
        private void loadHomeData() {
            new Thread(() -> {
                int pantryCount =
                        appDatabase.pantryDao()
                                .getAllPantryItems()
                                .size();
                runOnUiThread(() -> {
                    if (pantryCount == 1) {
                        pantryCountText.setText(
                                "1 Pantry item"
                        );
                    }else {
                        pantryCountText.setText(
                                pantryCount + "pantry items"
                        );
                    }
                    if (pantryCount == 0 ) {
                        homeSuggestionText.setText(
                                "Add pantry ingredients to unlock recipe suggestions"
                        );
                    } else {
                        homeSuggestionText.setText(
                                "Your pantry is ready, see what you can amek "
                        );
                    }
                });
            }).start();
    }

}

