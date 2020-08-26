package com.fadineg.trainingproject.news.model

import com.google.gson.annotations.SerializedName

data class Articles(
        val address: String,
        val date_from: String,
        val date_to: String,
        val description: String,
        val id: Int,
        val name: String,
        @SerializedName("org_name")
        val orgName: String,
        @SerializedName("org_site")
        val orgSite: String,
        @SerializedName("short_description")
        val shortDescription: String,
        @SerializedName("phone_numbers")
        val phoneNumbers : List<Phone_numbers>,
        val type: String
)