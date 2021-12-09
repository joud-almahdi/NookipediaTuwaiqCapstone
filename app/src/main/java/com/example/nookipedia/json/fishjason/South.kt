package com.example.nookipedia.json.fishjason


import com.google.gson.annotations.SerializedName

data class South(
    @SerializedName("availability_array")
    val availabilityArray: List<AvailabilityArrayX>,
    @SerializedName("months")
    val months: String,
    @SerializedName("months_array")
    val monthsArray: List<Int>,
    @SerializedName("times_by_month")
    val timesByMonth: TimesByMonthX
)