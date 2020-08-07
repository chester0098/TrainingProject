package com.fadineg.trainingproject.news.eventBus;

import com.fadineg.trainingproject.news.Filters;

import java.util.ArrayList;
import java.util.List;

public class FiltersBus {
    private List<Filters> filtersList = new ArrayList<>();

    public FiltersBus(List<Filters> filtersList) {
        this.filtersList = filtersList;
    }

    public List<Filters> getFiltersList() {
        return filtersList;
    }
}
