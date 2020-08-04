package com.fadineg.trainingproject.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fadineg.trainingproject.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FiltersRecyclerAdapter extends RecyclerView.Adapter<FiltersRecyclerAdapter.ViewHolder> {
    private List<Filters> filtersList;
    private Context context;

    FiltersRecyclerAdapter(List<Filters> filtersList, Context context) {
        this.filtersList = filtersList;
        this.context = context;
    }

    List<Filters> getFiltersList() {
        return filtersList;
    }

    @NotNull
    @Override
    public FiltersRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_filters_item, parent, false);
        return new FiltersRecyclerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NotNull final FiltersRecyclerAdapter.ViewHolder holder, final int position) {
        final Filters filters = filtersList.get(position);
        holder.category.setText(filters.getCategory());
        holder.aSwitch.setChecked(filters.getSwitchCheck());

        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                filtersList.get(position).setSwitchCheck(isChecked);
            }
        });
    }


    @Override
    public int getItemCount() {
        return filtersList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView category;
        Switch aSwitch;

        ViewHolder(View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.rv_filters_title);
            aSwitch = itemView.findViewById(R.id.rv_filters_switch);
        }
    }
}
