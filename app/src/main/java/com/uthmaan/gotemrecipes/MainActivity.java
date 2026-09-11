package com.uthmaan.gotemrecipes;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // temp open the entire pantry scree fpr test
        Intent openPantryScreen = new Intent(this, PantryActivity.class);
        startActivity(openPantryScreen);
        finish();


    }
}