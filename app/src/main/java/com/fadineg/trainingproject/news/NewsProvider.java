package com.fadineg.trainingproject.news;

import androidx.fragment.app.Fragment;

import java.util.List;

public interface NewsProvider {

    public void addFragment(Fragment fragment);

    public List<Filters> getFiltersList();

    public void setFiltersList(List<Filters> filtersList);

    public List<News> getNewsList();

    public void updateNewsAdapter();

}
