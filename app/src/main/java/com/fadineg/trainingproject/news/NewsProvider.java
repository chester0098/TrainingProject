package com.fadineg.trainingproject.news;

import androidx.fragment.app.Fragment;

import com.fadineg.trainingproject.news.model.Articles;
import com.fadineg.trainingproject.news.model.News;

import java.util.List;

public interface NewsProvider {

    public void openFilters();

    public List<Articles> getArticlesList();

    public List<News> getNewsList();

    public void setNewsList(List<News> newsList);

    public void updateNewsAdapter();

}
