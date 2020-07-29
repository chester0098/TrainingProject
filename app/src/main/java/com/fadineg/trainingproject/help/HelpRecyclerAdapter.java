package com.fadineg.trainingproject.help;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fadineg.trainingproject.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HelpRecyclerAdapter extends RecyclerView.Adapter<HelpRecyclerAdapter.ViewHolder> {
    private List<Help> helpList;

    HelpRecyclerAdapter(List<Help> helpList) {
        this.helpList = helpList;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_help_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NotNull final ViewHolder holder, final int position) {
        final Help help = helpList.get(position);
        holder.tvCategory.setText(help.getCategoryText());
        holder.ivCategoryImage.setImageResource(help.getCategoryImage());
    }


    @Override
    public int getItemCount() {
        return helpList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategory;
        ImageView ivCategoryImage;

        ViewHolder(View itemView) {
            super(itemView);
            tvCategory = itemView.findViewById(R.id.rv_help_category);
            ivCategoryImage = itemView.findViewById(R.id.rv_help_image);
        }
    }
}