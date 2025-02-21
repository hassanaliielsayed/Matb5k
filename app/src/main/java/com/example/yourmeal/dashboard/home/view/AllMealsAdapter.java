package com.example.yourmeal.dashboard.home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yourmeal.R;
import com.example.yourmeal.model.Meal;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;

public class AllMealsAdapter extends RecyclerView.Adapter<AllMealsAdapter.ViewHolder>{

    List<Meal> mealsList;
    OnMealItemClickListener onMealItemClickListener;

    public AllMealsAdapter(OnMealItemClickListener onMealItemClickListener) {
        mealsList = new ArrayList<>();
        this.onMealItemClickListener = onMealItemClickListener;
    }

    public void setMealsList(List<Meal> mealsList){
        this.mealsList = mealsList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.for_you_row_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtTitle.setText(mealsList.get(position).getStrMeal());
        holder.txtCategory.setText(holder.itemView.getContext().getString(R.string.category).concat(mealsList.get(position).getStrCategory()));
        holder.txtArea.setText(holder.itemView.getContext().getString(R.string.area).concat(mealsList.get(position).getStrArea()));

        Glide.with(holder.itemView)
                .load(mealsList.get(position).getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMealItemClickListener.onMealClicked(mealsList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtTitle, txtArea, txtCategory;
        ShapeableImageView imageView;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txt_title);
            txtArea = itemView.findViewById(R.id.txt_area);
            txtCategory = itemView.findViewById(R.id.txt_category);
            imageView = itemView.findViewById(R.id.img_card);
        }
    }
}
