package com.fadineg.trainingproject.domain.model.eventBus;

public class SearchStringBus {
    public String searchString;

    public SearchStringBus(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchString() {
        return searchString;
    }
}
