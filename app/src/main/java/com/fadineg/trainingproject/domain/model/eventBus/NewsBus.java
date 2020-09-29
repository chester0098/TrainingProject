package com.fadineg.trainingproject.domain.model.eventBus;

import com.fadineg.trainingproject.domain.model.news.News;

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
