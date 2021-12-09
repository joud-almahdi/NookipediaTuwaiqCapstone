package com.example.nookipedia.json.eventjson


import com.google.gson.annotations.SerializedName

data class eventjsonItem(
    @SerializedName("date")
    val date: String,
    @SerializedName("event")
    val event: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)