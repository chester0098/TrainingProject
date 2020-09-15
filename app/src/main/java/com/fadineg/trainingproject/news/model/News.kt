package com.fadineg.trainingproject.news.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
open class News(

        var articles: @RawValue RealmList<Articles>? = null,
        @SerializedName("category_name")
        var categoryName: String? = null,
        @SerializedName("category_type")
        var categoryType: String? = null,
        var id: Int? = 0,
        var categorySwitch: Boolean? = true
) : RealmObject(), Parcelable