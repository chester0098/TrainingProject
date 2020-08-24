package com.fadineg.trainingproject.news.model

data class News(

        val articles: List<Articles>,
        val category_name: String,
        val category_type: String,
        val id: Int,
        var _category_switch: Boolean
) {
    var category_switch: Boolean
        get() = _category_switch
        set(value) {
            _category_switch = value
        }
}