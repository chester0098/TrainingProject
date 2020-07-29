package com.fadineg.trainingproject.news;

import androidx.annotation.DrawableRes;

import java.util.ArrayList;
import java.util.List;

public class News {
    private Integer newsImage;
    private String newsTitle;
    private String newsDescription;

    public News(@DrawableRes Integer newsImage, String newsTitle, String newsDescription) {
        this.newsImage = newsImage;
        this.newsTitle = newsTitle;
        this.newsDescription = newsDescription;
    }

    public Integer getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(@DrawableRes Integer newsImage) {
        this.newsImage = newsImage;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

}

