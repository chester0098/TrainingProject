package com.fadineg.trainingproject.news.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject

open class News(

        var articles: RealmList<Articles>? = null,
        @SerializedName("category_name")
        var categoryName: String? = null,
        @SerializedName("category_type")
        var categoryType: String? = null,
        var id: Int? = 0,
        var categorySwitch: Boolean? = true
) : RealmObject()