package com.fadineg.trainingproject.help;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

public class Help {
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
