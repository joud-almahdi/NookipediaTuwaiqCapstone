package com.example.nookipedia.json.seajson


import com.google.gson.annotations.SerializedName

data class AvailabilityArrayX(
    @SerializedName("months")
    val months: String,
    @SerializedName("time")
    val time: String
)