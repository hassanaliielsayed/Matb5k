package com.example.yourmeal.searchresult.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.yourmeal.R;
import com.example.yourmeal.model.FilterMeals;
import com.google.android.material.imageview.ShapeableImageView;
import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder>{

    List<FilterMeals> mealList;
    OnMealClickListener searchResultFragment;

    public SearchResultAdapter(OnMealClickListener searchResultFragment){
        mealList = new ArrayList<>();
        this.searchResultFragment = searchResultFragment;

    }

    public void setData(List<FilterMeals> mealList){
        this.mealList = mealList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_item_search_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTitle.setText(mealList.get(position).getStrMeal());
        Glide.with(holder.itemView)
                .load(mealList.get(position).getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchResultFragment.onMealClicked(mealList.get(position).getidMeal());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtTitle;
        ShapeableImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            imageView = itemView.findViewById(R.id.img_card);
        }
    }
}
