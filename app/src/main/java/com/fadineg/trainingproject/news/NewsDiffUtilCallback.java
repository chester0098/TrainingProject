package com.fadineg.trainingproject.news;

import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class NewsDiffUtilCallback extends DiffUtil.Callback {
    private final List<News> oldList;
    private final List<News> newList;

    NewsDiffUtilCallback(List<News> oldList, List<News> newList) {
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
        News oldNews = oldList.get(oldItemPosition);
        News newNews = newList.get(newItemPosition);
        return oldNews.getId().equals(newNews.getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        News oldProduct = oldList.get(oldItemPosition);
        News newProduct = newList.get(newItemPosition);
        return oldProduct.getTitle().equals(newProduct.getTitle())
                && oldProduct.getDescription().equals(newProduct.getDescription());
    }
}
