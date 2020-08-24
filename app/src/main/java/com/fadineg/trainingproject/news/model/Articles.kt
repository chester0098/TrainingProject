package com.fadineg.trainingproject.news.model

data class Articles(
        val address: String,
        val date_from: String,
        val date_to: String,
        val description: String,
        val id: Int,
        val name: String,
        val org_name: String,
        val org_site: String,
        val short_description: String,
        val phone_numbers : List<Phone_numbers>,
        val type: String
)