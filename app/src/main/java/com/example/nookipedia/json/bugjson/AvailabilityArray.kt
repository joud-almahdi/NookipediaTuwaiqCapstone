package com.example.nookipedia.json.bugjson


import com.google.gson.annotations.SerializedName

data class AvailabilityArray(
    @SerializedName("months")
    val months: String,
    @SerializedName("time")
    val time: String
)