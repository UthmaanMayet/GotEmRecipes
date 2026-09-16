package com.uthmaan.gotemrecipes.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.uthmaan.gotemrecipes.R;
import com.uthmaan.gotemrecipes.model.Recipe;
import java.util.ArrayList;
import java.util.List;
public class RecipeAdapter
        extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {
        private List<Recipe> recipes;
        public interface OnRecipeClickListener{
            void onRecipeCLick(Recipe recipe);
        }
        private OnRecipeClickListener recipeClickListener;
        public RecipeAdapter(
                List<Recipe> recipes,
                OnRecipeClickListener recipeClickListener
        ) {
            this.recipeClickListener = recipeClickListener;
            if (recipes == null) {
                this.recipes = new ArrayList<>();
            } else {
                this.recipes = recipes;
            }
        }
        @NonNull
        @Override
        public RecipeViewHolder onCreateViewHolder(
                @NonNull ViewGroup parent,
                int viewType
        ) {
            View recipeView =
                    LayoutInflater.from(parent.getContext())
                            .inflate(
                                    R.layout.item_recipe,
                                    parent,
                                    false
                            );
            return new RecipeViewHolder(recipeView);
        }
        @Override
        public void onBindViewHolder(
                @NonNull RecipeViewHolder holder,
                int position
                ) {
            Recipe currentRecipe = recipes.get(position);
            holder.recipeNameText.setText(
                    currentRecipe.getRecipeName()
            );
            holder.recipeCategoryText.setText(
                    currentRecipe.getRecipeCategory()
            );
            holder.recipeDescriptionText.setText(
                    currentRecipe.getRecipeDescription()
            );
            holder.itemView.setOnClickListener(view ->
                    recipeClickListener.onRecipeCLick(currentRecipe)
            );
        }
        @Override
        public int getItemCount() {
            return recipes.size();
        }
        public void setRecipes(List<Recipe>recipes) {
            if (recipes == null) {
                this.recipes = new ArrayList<>();
            }else {
                this.recipes = recipes;
            }
            notifyDataSetChanged();
        }
        static class RecipeViewHolder
                extends RecyclerView.ViewHolder{
            TextView recipeNameText;
            TextView recipeCategoryText;
            TextView recipeDescriptionText;

            public  RecipeViewHolder(
                    @NonNull View itemView
            ) {
                super(itemView);
                recipeNameText =
                        itemView.findViewById(
                                R.id.textRecipeName
                        );
                recipeCategoryText =
                        itemView.findViewById(
                                R.id.textRecipeCategory
                        );
                recipeDescriptionText =
                        itemView.findViewById(
                                R.id.textRecipeDescription
                        );
            }
        }

}
