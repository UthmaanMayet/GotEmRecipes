package com.uthmaan.gotemrecipes;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import com.google.android.material.bottomnavigation.BottomNavigationView;
public class SettingsActivity  extends AppCompatActivity{
    private Button backButton;
    private SwitchCompat expiryWarningsSwitch;
    private SwitchCompat recipeSuggestionsSwitch;
    private SharedPreferences sharedPreferences;
    private BottomNavigationView bottomNavigationView;
    private static final String PREFS_NAME ="got_em_settings";
    private static final String KEY_EXPIRY_WARNINGS ="expiry_warnings";
    private static final String KEY_RECIPE_SUGGESTIONS = "recipe_suggestions";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        backButton =
                findViewById(R.id.buttonSettingsBack);
        expiryWarningsSwitch =
                findViewById(R.id.switchExpiryWarnings);
        recipeSuggestionsSwitch =
                findViewById(R.id.switchRecipeNotifications);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.navSettings);
        bottomNavigationView.setOnItemSelectedListener(item ->{
            int itemId = item.getItemId();
            if(itemId == R.id.navHome) {
                startActivity(new Intent(SettingsActivity.this, MainActivity.class));
                return  true;
            } else if (itemId == R.id.navPantry){
                startActivity(new Intent(SettingsActivity.this, PantryActivity.class));
                return true;
            } else if (itemId == R.id.navRecipes) {
                startActivity(new Intent(SettingsActivity.this, AllRecipesActivity.class));
                return true;
            } else if (itemId == R.id.navSettings){
                return true;
            }
            return false;
        });
        sharedPreferences =
                getSharedPreferences(
                        PREFS_NAME,
                        MODE_PRIVATE
                );
        boolean expiryWarningsEnabled =
                sharedPreferences.getBoolean(
                        KEY_EXPIRY_WARNINGS,
                        true
                );
        boolean recipeSuggestionsEnabled =
                sharedPreferences.getBoolean(
                        KEY_RECIPE_SUGGESTIONS,
                        true
                );
        expiryWarningsSwitch.setChecked(expiryWarningsEnabled);
        recipeSuggestionsSwitch.setChecked(recipeSuggestionsEnabled);
        backButton.setOnClickListener(view ->
                finish()
        );
        expiryWarningsSwitch.setOnCheckedChangeListener(
                (buttonView, isChecked) ->
                sharedPreferences.edit()
                        .putBoolean(
                                KEY_EXPIRY_WARNINGS,
                                isChecked
                        )
                        .apply()
                );
                recipeSuggestionsSwitch.setOnCheckedChangeListener(
                        (buttonView, isChecked) ->
                                sharedPreferences.edit()
                                        .putBoolean(
                                                KEY_RECIPE_SUGGESTIONS,
                                                isChecked
                                        )
                                        .apply()
                );

    }
}
