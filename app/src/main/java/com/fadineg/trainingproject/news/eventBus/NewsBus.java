package com.fadineg.trainingproject.news.eventBus;

import com.fadineg.trainingproject.news.News;

import java.util.ArrayList;
import java.util.List;

public class NewsBus {
    private List<News> newsList = new ArrayList<>();

    public NewsBus(List<News> newsList) {
        this.newsList = newsList;
    }

    public List<News> getNewsList() {
        return newsList;
    }
}
