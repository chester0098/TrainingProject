package com.fadineg.trainingproject.news;

import io.realm.Realm;

public interface NewsProvider {

    void openFilters();

    void updateNewsAdapter();

    public Realm getRealm();

}
