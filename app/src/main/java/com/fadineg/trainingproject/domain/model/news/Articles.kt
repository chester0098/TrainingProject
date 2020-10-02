package com.fadineg.trainingproject.domain.model.news

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
open class Articles(
        var address: String? = null,
        var date_from: String? = null,
        @SerializedName("date_to")
        var dateTo: String? = null,
        var description: String? = null,
        var id: Int? = 0,
        var name: String? = null,
        @SerializedName("org_name")
        var orgName: String? = null,
        @SerializedName("org_site")
        var orgSite: String? = null,
        @SerializedName("short_description")
        var shortDescription: String? = null,
        @SerializedName("phone_numbers")
        var phoneNumbers: @RawValue RealmList<PhoneNumbers>? = null,
        var type: String? = null
) : RealmObject(), Parcelable