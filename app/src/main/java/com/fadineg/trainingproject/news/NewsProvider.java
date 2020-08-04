package com.fadineg.trainingproject.news;

import java.util.List;

public interface NewsProvider {
    public List<Filters> getFiltersList();

    public List<News> getNewsList();
}
