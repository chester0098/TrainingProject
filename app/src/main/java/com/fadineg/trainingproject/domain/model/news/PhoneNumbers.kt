package com.fadineg.trainingproject.domain.model.news

import android.os.Parcelable
import io.realm.RealmObject
import kotlinx.android.parcel.Parcelize

@Parcelize
open class PhoneNumbers(

		var id: Int? = 0,
		var number: String? = null
) : RealmObject(), Parcelable