package com.uthmaan.gotemrecipes.utils;
import com.uthmaan.gotemrecipes.model.PantryItem;
import com.uthmaan.gotemrecipes.model.Recipe;
import com.uthmaan.gotemrecipes.model.RecipeIngredient;
import java.util.ArrayList;
import java.util.List;
public class RecipeMatcher {
    // This is here to return only the recipes that can actually be fullly made by the pantries items and content
    public static List<Recipe> findMatchingRecipes(
            List<Recipe> recipes,
            List<PantryItem> pantryItems,
            List<RecipeIngredient>recipeIngredients
    ) {
        List<Recipe> matchingRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            boolean recipeCanBeMade = true;
            for (RecipeIngredient requiredIngredient : recipeIngredients) {
                // this will ignore the ingredients that dont belong to the current recipes
                if (requiredIngredient.getRecipeId() != recipe.getRecipeId()) {
                    continue;
                }
                boolean ingredientFound = false;
                for (PantryItem pantryItem : pantryItems) {
                    String pantryIngredientName =
                            normalizeIngredientName(
                                    pantryItem.getIngredientName()
                            );
                    String requiredIngredientName =
                            normalizeIngredientName(
                                    requiredIngredient.getIngredientName()
                            );
                    if (pantryIngredientName.equals(requiredIngredientName)) {
                        ingredientFound = true;
                        boolean enoughQuantity =
                                hasEnoughQuantity(
                                        pantryItem.getPantryQuantity(),
                                        pantryItem.getMeasurementUnit(),
                                        requiredIngredient.getRequiredQuantity(),
                                        requiredIngredient.getMeasurementUnit()
                                );
                        if (!enoughQuantity) {
                            recipeCanBeMade = false;
                        }
                        break;
                    }
                }
                if (!ingredientFound) {
                    recipeCanBeMade = false;
                }
                if (!recipeCanBeMade) {
                    break;
                }
            }
            if (recipeCanBeMade) {
                matchingRecipes.add(recipe);
            }
        }
        return matchingRecipes;
            }
    // This is here to clean the ingredient names so the basic differencs do not break the matching sequences

    private static String normalizeIngredientName(String ingredientName) {
        if(ingredientName ==null) {
            return "" ;
        }
        String normalizedName =
                ingredientName.trim().toLowerCase();
        normalizedName =
                normalizedName.replaceAll("\\s+", " ");
        // this will be here to handle the common plural forms that are used in the recipes databases.
        if (normalizedName.endsWith("ies")){
            normalizedName =
                    normalizedName.substring(
                            0,
                            normalizedName.length() -3
                    ) + "y" ;
        } else if (normalizedName.endsWith("oes")) {
            normalizedName =
                    normalizedName.substring(
                            0,
                            normalizedName.length() - 2
                    );
        } else if (normalizedName.endsWith("es")
                && normalizedName.length() >4) {
        normalizedName =
                normalizedName.substring(
                        0,
                        normalizedName.length() - 2
                );
            }else if (normalizedName.endsWith("s")
                    && normalizedName.length() > 3) {
                normalizedName =
                        normalizedName.substring(
                                0,
                                normalizedName.length() - 1
                        );
            }
            return normalizedName;
        }
        // This is here to check the quantity after you convert the compatible units.
    private static boolean hasEnoughQuantity(
            double pantryQuantity,
                    String pantryUnit,
                      double requiredQuantity,
                      String requiredUnit
            ){
        String normalizedPantryUnit =
                normalizeUnit(pantryUnit);
        String normalizedRequiredUnit =
                normalizeUnit(requiredUnit);
        // This is so that the same units can be compared directly
        if (normalizedPantryUnit.equals(normalizedRequiredUnit)) {
            return pantryQuantity >= requiredQuantity;
        }
        String pantryUnitGroup =
                getUnitGroup(normalizedPantryUnit);
        String requiredUnitGroup =
                getUnitGroup(normalizedRequiredUnit);
        // this is here to state that the measurement types cannot be compared and used against each other
        if (!pantryUnitGroup.equals(requiredUnitGroup)) {
            return false;
        }
        double convertedPantryQuantity =
                convertToBaseUnit(
                        pantryQuantity,
                        normalizedPantryUnit
                );
        double convertedRequiredQuantity =
                convertToBaseUnit(
                        requiredQuantity,
                        normalizedRequiredUnit
                );
        return convertedPantryQuantity
                >= convertedRequiredQuantity;
    }
    // This is to standardize the units before the overall comparision
    private static String normalizeUnit(String unit) {
        if(unit == null) {
            return  "" ;
        }
        return unit.trim().toLowerCase();
    }
    // This is here to group together the units that can be converted without issues
    private static String getUnitGroup(String unit) {
        switch (unit) {
            case "g" :
            case "kg" :
                return "weight" ;
            case "ml" :
            case "l" :
            case"tbsp" :
            case "tsp" :
            case "cups":
                return "volume" ;
            case "unit":
            case "units" :
                return "count";
            default:
                return "unknown";
        }
    }
    // This is here to convert the units that are compatible into a common based measurement
    private static double convertToBaseUnit(
            double quantity,
                    String unit
            ){
        switch (unit) {
            case "kg" :
                return quantity * 1000;
            case "g" :
                return quantity;
            case "l" :
                return quantity * 1000;
            case "ml" :
                return quantity;
            case "tbsp" :
                return quantity * 15;
            case "tsp" :
                return quantity * 5;
            case "cups" :
                return quantity * 240;
            case "unit" :
            case "units" :
                return quantity;
            default:
                return quantity;
        }
    }
}