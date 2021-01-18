package com.fadineg.trainingproject.domain.model;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

public class Help {
    private Integer categoryText;
    private Integer categoryImage;

    public Help(@StringRes Integer categoryText, @DrawableRes Integer categoryImage) {
        this.categoryText = categoryText;
        this.categoryImage = categoryImage;
    }

    public Integer getCategoryText() {
        return categoryText;
    }

    public void setCategoryText(@StringRes Integer categoryText) {
        this.categoryText = categoryText;
    }

    public Integer getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(@DrawableRes Integer categoryImage) {
        this.categoryImage = categoryImage;
    }
}
