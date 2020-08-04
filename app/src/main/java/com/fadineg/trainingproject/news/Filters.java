package com.fadineg.trainingproject.news;

public class Filters {
    private String category;
    private Boolean switchCheck;

    public Filters(String category, Boolean switchCheck) {
        this.category = category;
        this.switchCheck = switchCheck;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getSwitchCheck() {
        return switchCheck;
    }

    public void setSwitchCheck(Boolean switchCheck) {
        this.switchCheck = switchCheck;
    }
}
