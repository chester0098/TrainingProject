package com.fadineg.trainingproject.news;

import com.fadineg.trainingproject.RealmManager;

public interface NewsProvider {

    void openFilters();

    void updateNewsAdapter();

    public RealmManager getRealmManager();

}
