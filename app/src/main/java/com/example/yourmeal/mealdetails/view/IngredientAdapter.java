package com.example.yourmeal.mealdetails.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yourmeal.R;
import com.example.yourmeal.model.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.ViewHolder> {

    List<Ingredient> ingredientsList;

    public IngredientAdapter(){

        ingredientsList = new ArrayList<>();
    }


    public void setIngredient(List<Ingredient> ingredientsList) {
        this.ingredientsList = ingredientsList;
        Log.d("asd --> ", "setIngredient: " + ingredientsList.size());
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_row_item, parent, false);
        return new ViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtMeasure.setText(ingredientsList.get(position).getMeasure());
        holder.txtIngredient.setText(ingredientsList.get(position).getName());

        Glide.with(holder.itemView)
                .load(ingredientsList.get(position).getImg())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.imgIngredient);

    }

    @Override
    public int getItemCount() {
        return ingredientsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgIngredient;
        TextView txtIngredient, txtMeasure;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIngredient = itemView.findViewById(R.id.imgIngredient);
            txtIngredient = itemView.findViewById(R.id.txtIngredient);
            txtMeasure = itemView.findViewById(R.id.txtMeasure);
        }
    }
}
