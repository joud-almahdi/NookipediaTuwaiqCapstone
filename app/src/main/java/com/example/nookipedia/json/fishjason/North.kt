package com.example.nookipedia.json.fishjason


import com.google.gson.annotations.SerializedName

data class North(
    @SerializedName("availability_array")
    val availabilityArray: List<AvailabilityArray>,
    @SerializedName("months")
    val months: String,
    @SerializedName("months_array")
    val monthsArray: List<Int>,
    @SerializedName("times_by_month")
    val timesByMonth: TimesByMonth
)