package com.example.nookipedia.json.fishjason


import com.google.gson.annotations.SerializedName

data class fishjsonItem(
    @SerializedName("catchphrases")
    val catchphrases: List<String>,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("north")
    val north: North,
    @SerializedName("number")
    val number: Int,
    @SerializedName("rarity")
    val rarity: String,
    @SerializedName("render_url")
    val renderUrl: String,
    @SerializedName("sell_cj")
    val sellCj: Int,
    @SerializedName("sell_nook")
    val sellNook: Int,
    @SerializedName("shadow_size")
    val shadowSize: String,
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