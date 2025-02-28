package com.example.yourmeal.dashboard.search.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yourmeal.R;
import com.example.yourmeal.model.SearchItem;

import java.util.ArrayList;
import java.util.List;

public class SearchFragmentAdapter extends RecyclerView.Adapter<SearchFragmentAdapter.ViewHolder> {

    List<SearchItem> searchItemList;

    final OnItemSearchClickInterface searchFragment;


    public SearchFragmentAdapter(OnItemSearchClickInterface searchFragment) {
        this.searchFragment = searchFragment;
        searchItemList = new ArrayList<>();
    }

    public void setSearchItemList(List<SearchItem> searchItemList) {
        this.searchItemList = searchItemList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_item_search, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtIncoming.setText(searchItemList.get(position).getName());

        Glide.with(holder.itemView)
                .load(searchItemList.get(position).getImg())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.imgSearchItem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchFragment.onItemClicked(searchItemList.get(position).getName());
            }
        });



    }

    @Override
    public int getItemCount() {
        return searchItemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgSearchItem;
        TextView txtIncoming;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgSearchItem = itemView.findViewById(R.id.imgSearchItem);
            txtIncoming = itemView.findViewById(R.id.txtIncoming);
        }
    }
}
