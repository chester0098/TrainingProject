package com.fadineg.trainingproject.help;

import com.fadineg.trainingproject.R;

import java.util.ArrayList;
import java.util.List;

class HelpList {

    List<Help> createdHelpList() {
        List<Help> helpList = new ArrayList<>();
        helpList.add(new Help("Дети", R.drawable.children));
        helpList.add(new Help("Взрослые", R.drawable.adults));
        helpList.add(new Help("Пожилые", R.drawable.elderly));
        helpList.add(new Help("Животные", R.drawable.animals));
        helpList.add(new Help("Мероприятия", R.drawable.event));
        return helpList;
    }
}

class Help {
    private String categoryText;
    private Integer categoryImage;

    Help(String categoryText, Integer categoryImage) {
        this.categoryText = categoryText;
        this.categoryImage = categoryImage;
    }

    String getCategoryText() {
        return categoryText;
    }

    public void setCategoryText(String categoryText) {
        this.categoryText = categoryText;
    }

    Integer getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(Integer categoryImage) {
        this.categoryImage = categoryImage;
    }
}
