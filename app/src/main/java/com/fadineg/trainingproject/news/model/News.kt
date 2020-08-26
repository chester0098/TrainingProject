package com.fadineg.trainingproject.news.model

import com.google.gson.annotations.SerializedName

data class News(

        val articles: List<Articles>,
        @SerializedName("category_name")
        val categoryName: String,
        @SerializedName("category_type")
        val categoryType: String,
        val id: Int,
        var categorySwitch: Boolean
)