package com.fadineg.trainingproject.help;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import com.fadineg.trainingproject.R;

import java.util.ArrayList;
import java.util.List;

class HelpList {

    List<Help> createdHelpList() {
        List<Help> helpList = new ArrayList<>();
        helpList.add(new Help(R.string.helpList_category_children, R.drawable.children));
        helpList.add(new Help(R.string.helpList_category_adults, R.drawable.adults));
        helpList.add(new Help(R.string.helpList_category_elderly, R.drawable.elderly));
        helpList.add(new Help(R.string.helpList_category_animals, R.drawable.animals));
        helpList.add(new Help(R.string.helpList_category_event, R.drawable.event));
        return helpList;
    }
}

class Help {
    private Integer categoryText;
    private Integer categoryImage;

    Help(@StringRes Integer categoryText, @DrawableRes Integer categoryImage) {
        this.categoryText = categoryText;
        this.categoryImage = categoryImage;
    }

    Integer getCategoryText() {
        return categoryText;
    }

    public void setCategoryText(@StringRes Integer categoryText) {
        this.categoryText = categoryText;
    }

    Integer getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(@DrawableRes Integer categoryImage) {
        this.categoryImage = categoryImage;
    }
}
