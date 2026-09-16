package com.uthmaan.gotemrecipes;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
public class SettingsActivity  extends AppCompatActivity{
    private Button backButton;
    private SwitchCompat expiryWarningsSwitch;
    private SwitchCompat recipeSuggestionsSwitch;
    private SharedPreferences sharedPreferences;
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
