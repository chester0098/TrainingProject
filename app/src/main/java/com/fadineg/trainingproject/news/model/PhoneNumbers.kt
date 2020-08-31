package com.fadineg.trainingproject.news.model

import io.realm.RealmObject

open class PhoneNumbers(

		var id: Int? = 0,
		var number: String? = null
) : RealmObject()