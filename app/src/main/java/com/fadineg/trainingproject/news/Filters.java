package com.fadineg.trainingproject.news;

public class Filters {
    private String category;
    private Boolean switch_check;

    public Filters(String category, Boolean switch_check){
        this.category = category;
        this.switch_check = switch_check;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getSwitch_check() {
        return switch_check;
    }

    public void setSwitch_check(Boolean switch_check) {
        this.switch_check = switch_check;
    }
}
