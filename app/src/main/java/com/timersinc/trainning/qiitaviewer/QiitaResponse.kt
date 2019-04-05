package com.timersinc.trainning.qiitaviewer

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item(
    val title: String
)

@JsonClass(generateAdapter = true)
data class User(
    val id: String,
    val profile_image_url: String
)
