package com.fadineg.trainingproject.news;

import androidx.recyclerview.widget.DiffUtil;

import com.fadineg.trainingproject.news.model.Articles;

import java.util.List;

public class NewsDiffUtilCallback extends DiffUtil.Callback {
    private final List<Articles> oldList;
    private final List<Articles> newList;

    public NewsDiffUtilCallback(List<Articles> oldList, List<Articles> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Articles oldNews = oldList.get(oldItemPosition);
        Articles newNews = newList.get(newItemPosition);
        return oldNews.getId()==(newNews.getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Articles oldProduct = oldList.get(oldItemPosition);
        Articles newProduct = newList.get(newItemPosition);
        return oldProduct.getName().equals(newProduct.getName())
                && oldProduct.getDescription().equals(newProduct.getDescription());
    }
}
