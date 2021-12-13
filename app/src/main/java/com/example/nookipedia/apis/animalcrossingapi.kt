package com.example.nookipedia.apis

import com.example.nookipedia.json.bugjson.bugjsonItem
import com.example.nookipedia.json.fishjason.fishjsonItem
import com.example.nookipedia.json.seajson.seajsonItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface animalcrossingapi {

    @Headers("X-API-KEY: 91fd543a-28da-4b8d-af55-07a3d9fd6670")
    @GET("/nh/fish")
   suspend fun getfish(): Response<List<fishjsonItem>>

    @Headers("X-API-KEY: 91fd543a-28da-4b8d-af55-07a3d9fd6670")
    @GET("/nh/fish")
    suspend fun getonefish():fishjsonItem



    @Headers("X-API-KEY: 91fd543a-28da-4b8d-af55-07a3d9fd6670")
    @GET("/nh/bugs")
    suspend fun getbug(): Response<List<bugjsonItem>>


    @Headers("X-API-KEY: 91fd543a-28da-4b8d-af55-07a3d9fd6670")
    @GET("/nh/bugs")
    suspend fun getonebug():bugjsonItem


    @Headers("X-API-KEY: 91fd543a-28da-4b8d-af55-07a3d9fd6670")
    @GET("/nh/sea")
    suspend fun getsea(): Response<List<seajsonItem>>

    @Headers("X-API-KEY: 91fd543a-28da-4b8d-af55-07a3d9fd6670")
    @GET("/nh/sea")
    suspend fun getonesea():seajsonItem


}