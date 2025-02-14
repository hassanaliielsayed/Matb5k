package com.example.yourmeal.onboarding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yourmeal.R;

import java.util.List;

public class VPAdapter extends RecyclerView.Adapter<VPAdapter.ViewHolder> {

    List<ViewPagerItem> viewPagerItemsList;

    public VPAdapter(List<ViewPagerItem> viewPagerItemsList){
        this.viewPagerItemsList = viewPagerItemsList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pager_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(viewPagerItemsList.get(position).imageId);
        holder.txtHeading.setText(viewPagerItemsList.get(position).heading);

    }

    @Override
    public int getItemCount() {
        return viewPagerItemsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView txtHeading;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgView);
            txtHeading = itemView.findViewById(R.id.txtHeading);
        }
    }
}
