package com.uthmaan.gotemrecipes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.uthmaan.gotemrecipes.R;
import com.uthmaan.gotemrecipes.model.PantryItem;
import java.util.ArrayList;
import java.util.List;

public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.PantryViewHolder> {
    private List<PantryItem> pantryItems = new ArrayList<>();
    @NonNull
    @Override
    public PantryViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        // This is here to create an item row in the pantry
        View pantryItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pantry,parent, false);
        return new PantryViewHolder(pantryItemView);
    }
    @Override
    public void onBindViewHolder(@NonNull PantryViewHolder holder, int position){
        PantryItem currentPantryItem = pantryItems.get(position);
        // This is here as it places the pantry item data into the correct view points
        holder.ingredientNameText.setText(currentPantryItem.getIngredientName());
        String quantityDisplay = currentPantryItem.getPantryQuantity()
                + " "
                + currentPantryItem.getMeasurementUnit();
        holder.ingredientQuantityText.setText(quantityDisplay);
        holder.ingredientCategoryText.setText(currentPantryItem.getIngredientCategory());
        holder.ingredientExpiryText.setText(currentPantryItem.getExpiryDate());
    }
    @Override
    public int getItemCount() {
        return pantryItems.size();
    }
    // This is to update the recycler viewer whenever the fresh pantry data ends up being loaded

    public void setPantryItems(List<PantryItem> updatedPantryItems) {
        pantryItems = updatedPantryItems;
        notifyDataSetChanged();
    }
    static class  PantryViewHolder extends RecyclerView.ViewHolder{
        private final TextView ingredientNameText;
        private final TextView ingredientQuantityText;
        private final TextView ingredientCategoryText;
        private final TextView ingredientExpiryText;

        public PantryViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientNameText = itemView.findViewById(R.id.textIngredientName);
            ingredientQuantityText = itemView.findViewById(R.id.textIngredientQuantity);
            ingredientCategoryText = itemView.findViewById(R.id.textIngredientCategory);
            ingredientExpiryText = itemView.findViewById(R.id.textIngredientExpiry);
        }

    }
}