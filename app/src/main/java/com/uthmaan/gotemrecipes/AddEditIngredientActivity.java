package com.uthmaan.gotemrecipes;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.uthmaan.gotemrecipes.database.AppDatabase;
import com.uthmaan.gotemrecipes.model.PantryItem;
import java.util.Calendar;
public class AddEditIngredientActivity extends AppCompatActivity{
    private EditText ingredientNameInput;
    private EditText ingredientQuantityInput;
    private EditText expiryDateInput;
    private Spinner measurementUnitSpinner;
    private Spinner ingredientCategorySpinner;
    private Button saveIngredientButton;
    private AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_ingredient);
        // This is here to link the java variables to the field in the XML layout
        ingredientNameInput = findViewById(R.id.editIngredientName);
        ingredientQuantityInput = findViewById(R.id.editIngredientQuantity);
        expiryDateInput = findViewById(R.id.editExpiryDate);
        measurementUnitSpinner = findViewById(R.id.spinnerMeasurementUnit);
        ingredientCategorySpinner = findViewById(R.id.spinnerIngredientCategory);
        saveIngredientButton = findViewById(R.id.buttonSaveIngredient);
        appDatabase = AppDatabase.getDatabase(this);
        setupMeasurementUnits();
        setupIngredientCategories();
        setupExpiryDatePicker();
        saveIngredientButton.setOnClickListener(view -> savePantryItem());
    }
    private void setupMeasurementUnits() {
        String[] measurementUnits = {
                "g",
                "kg",
                "ml",
                "L",
                "units",
                "tbsp",
                "tsp",
                "cups"
        };
        ArrayAdapter<String> unitAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                measurementUnits
        );
        unitAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        measurementUnitSpinner.setAdapter(unitAdapter);
    }
    private void setupIngredientCategories()  {
            String[] ingredientCategories = {
                    "Meat",
                    "Diary",
                    "Vegetables",
                    "Fruit",
                    "Grain and Pasta",
                    "Bakery" ,
                    "Canned Goods",
                    "Condiments",
                    "Spices",
                    "Other"
    };
            ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_spinner_item,
                    ingredientCategories
            );
            categoryAdapter.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item
            );
            ingredientCategorySpinner.setAdapter(categoryAdapter);
    }
    private void setupExpiryDatePicker(){
        expiryDateInput.setOnClickListener(view -> {
            Calendar currentDate = Calendar.getInstance();
            int year = currentDate.get(Calendar.YEAR);
            int month = currentDate.get(Calendar.MONTH);
            int day = currentDate.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (DatePicker, selectedYear, selectedMonth, selectedDay) -> {
                        String selectedDate =
                                selectedDay + "/" +
                                        (selectedMonth + 1) + "/" +
                                        selectedYear;
                        expiryDateInput.setText(selectedDate);
                    },
                    year,
                    month,
                    day
            );
            datePickerDialog.show();
        });

    }
    private void savePantryItem() {
        String ingredientName =
                ingredientNameInput.getText().toString().trim();
        String quantityText =
                ingredientQuantityInput.getText().toString().trim();
        String measurementUnit =
                measurementUnitSpinner.getSelectedItem().toString();
        String ingredientCategory =
                ingredientCategorySpinner.getSelectedItem().toString();
        String expiryDate =
                expiryDateInput.getText().toString().trim();
        // This is here to stop the form from saving anything thats wrong or incomplete
        if(ingredientName.isEmpty()) {
            ingredientNameInput.setError("The Ingredient name is required");
            ingredientNameInput.requestFocus();
            return;
        }
        if(quantityText.isEmpty()) {
            ingredientQuantityInput.setError("Quantity is required");
            ingredientQuantityInput.requestFocus();
            return;

        }
        double pantryQuantity;
        try{
            pantryQuantity = Double.parseDouble(quantityText);
        } catch (NumberFormatException exception) {
            ingredientQuantityInput.setError("Please enter a valid quantity");
            return;
        }
        if (pantryQuantity <=0) {
            ingredientQuantityInput.setError("The Quantity must be greater than 0");
            return;
        }
        PantryItem newPantryItem = new PantryItem(
                ingredientName,
                pantryQuantity,
                measurementUnit,
                ingredientCategory,
                expiryDate
        );
        // The work from room database is handled from the main UI threads
        new Thread(() -> {
            appDatabase.pantryDao().addPantryItem(newPantryItem);
            runOnUiThread(() -> {
                Toast.makeText(
                        this,
                        "The ingredient is added to your pantry",
                        Toast.LENGTH_SHORT
                ).show();
                finish();
            });
        }).start();
    }
}