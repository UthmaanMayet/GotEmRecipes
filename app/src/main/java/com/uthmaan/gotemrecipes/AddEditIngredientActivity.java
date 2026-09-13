package com.uthmaan.gotemrecipes;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.uthmaan.gotemrecipes.database.AppDatabase;
import com.uthmaan.gotemrecipes.model.PantryItem;
import java.util.Calendar;
public class AddEditIngredientActivity extends AppCompatActivity {
    private EditText ingredientNameInput;
    private EditText ingredientQuantityInput;
    private EditText expiryDateInput;
    private Spinner measurementUnitSpinner;
    private Spinner ingredientCategorySpinner;
    private Button saveIngredientButton;
    private Button deleteIngredientButton;
    private AppDatabase appDatabase;
    private int pantryItemId = -1;
    private boolean editingExistingItem = false;

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
        deleteIngredientButton= findViewById(R.id.buttonDeleteIngredient);
        appDatabase = AppDatabase.getDatabase(this);
        setupMeasurementUnits();
        setupIngredientCategories();
        setupExpiryDatePicker();
        Intent receivedIntent = getIntent();
        if (receivedIntent.hasExtra("PANTRY_ITEM_ID")) {
            editingExistingItem = true;
            pantryItemId =
                    receivedIntent.getIntExtra("PANTRY_ITEM_ID", -1);
            ingredientNameInput.setText(
                    receivedIntent.getStringExtra("INGREDIENT_NAME")
            );
            double existingQuantity =
                    receivedIntent.getDoubleExtra("PANTRY_QUANTITY", 0);
            ingredientQuantityInput.setText(
                    String.valueOf(existingQuantity)
            );
            expiryDateInput.setText(
                    receivedIntent.getStringExtra("EXPIRY_DATE")
            );
            selectSpinnerValue(
                    measurementUnitSpinner,
                    receivedIntent.getStringExtra("MEASUREMENT_UNIT")
            );
            selectSpinnerValue(
                    ingredientCategorySpinner,
                    receivedIntent.getStringExtra("INGREDIENT_CATEGORY")
            );
            saveIngredientButton.setText("UPDATE INGREDIENT");
            deleteIngredientButton.setVisibility(View.VISIBLE);
            deleteIngredientButton.setOnClickListener(view ->
                    confirmDeleteIngredient()
            );
        }
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

    private void setupIngredientCategories() {
        String[] ingredientCategories = {
                "Meat",
                "Diary",
                "Vegetables",
                "Fruit",
                "Grain and Pasta",
                "Bakery",
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

    private void selectSpinnerValue(Spinner spinner, String value) {
        if (value == null) {
            return;
        }
        for (int position = 0; position < spinner.getCount(); position++) {
            if (spinner.getItemAtPosition(position)
                    .toString()
                    .equals(value)) {
                spinner.setSelection(position);
                break;
            }
        }
    }

    private void setupExpiryDatePicker() {
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
        // This is here to stop the form from saving anything that is wrong or incomplete
        if (ingredientName.isEmpty()) {
            ingredientNameInput.setError("The Ingredient name is required");
            ingredientNameInput.requestFocus();
            return;
        }
        if (ingredientName.length() < 2) {
            ingredientNameInput.setError("Enter a valid ingredient name");
            ingredientNameInput.requestFocus();
            return;
        }
        if (quantityText.isEmpty()) {
            ingredientQuantityInput.setError("Quantity is required");
            ingredientQuantityInput.requestFocus();
            return;

        }
        double pantryQuantity;
        try {
            pantryQuantity = Double.parseDouble(quantityText);
        } catch (NumberFormatException exception) {
            ingredientQuantityInput.setError("Please enter a valid quantity");
            return;
        }
        if (pantryQuantity <= 0) {
            ingredientQuantityInput.setError("The Quantity must be greater than 0");
            return;
        }
        if (pantryQuantity >100000) {
            ingredientQuantityInput.setError("Quantity is to large");
            ingredientQuantityInput.requestFocus();
            return;
        }
        PantryItem pantryItemToSave = new PantryItem(
                ingredientName,
                pantryQuantity,
                measurementUnit,
                ingredientCategory,
                expiryDate
        );
        if (editingExistingItem) {
            pantryItemToSave.setPantryItemId(pantryItemId);
        }
        // The work from room database is handled from the main UI threads
        new Thread(() -> {
            if (editingExistingItem) {
                appDatabase.pantryDao().updatePantryItem(pantryItemToSave);
            } else {
                appDatabase.pantryDao().addPantryItem(pantryItemToSave);
            }
            runOnUiThread(() -> {
                String message;
                if (editingExistingItem) {
                    message = "Ingredient updated";
                } else {
                    message = "Ingredient added to your pantry";
                }
                Toast.makeText(
                        this,
                        message,
                        Toast.LENGTH_SHORT
                ).show();
                finish();
            });
        }).start();
    }
    private void confirmDeleteIngredient() {
        new AlertDialog.Builder(this)
                .setTitle("Delete Ingredient")
                .setMessage("Are you sure that you want to remove this ingrient from the pantry")
                .setPositiveButton("Delete", (dialog, which) -> {
                    deletePantryItem();
                })
                .setNegativeButton("Cancel",null)
                .show();
    }
    private void deletePantryItem() {
        PantryItem pantryItemToDelete = new PantryItem(
                ingredientNameInput.getText().toString().trim(),
                Double.parseDouble(
                        ingredientQuantityInput.getText().toString().trim()
                ),
                measurementUnitSpinner.getSelectedItem().toString(),
                ingredientCategorySpinner.getSelectedItem().toString(),
                expiryDateInput.getText().toString().trim()
        );
        pantryItemToDelete.setPantryItemId(pantryItemId);
        new Thread(() -> {
            appDatabase.pantryDao()
                    .deletePantryItem(pantryItemToDelete);
            runOnUiThread(() -> {
                Toast.makeText(
                        this,
                        "Ingredient removed",
                        Toast.LENGTH_SHORT
                ).show();
                finish();
            });
        }).start();
    }
}