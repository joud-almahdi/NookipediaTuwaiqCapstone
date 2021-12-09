package com.example.nookipedia.json.bugjson


import com.google.gson.annotations.SerializedName

data class bugjsonItem(
    @SerializedName("catchphrases")
    val catchphrases: List<String>,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("north")
    val north: North,
    @SerializedName("number")
    val number: Int,
    @SerializedName("rarity")
    val rarity: String,
    @SerializedName("render_url")
    val renderUrl: String,
    @SerializedName("sell_flick")
    val sellFlick: Int,
    @SerializedName("sell_nook")
    val sellNook: Int,
    @SerializedName("south")
    val south: South,
    @SerializedName("tank_length")
    val tankLength: Double,
    @SerializedName("tank_width")
    val tankWidth: Double,
    @SerializedName("total_catch")
    val totalCatch: Int,
    @SerializedName("url")
    val url: String
)