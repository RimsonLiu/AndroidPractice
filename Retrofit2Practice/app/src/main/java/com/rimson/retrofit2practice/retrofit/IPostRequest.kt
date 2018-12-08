package com.rimson.retrofit2practice.retrofit

import com.rimson.retrofit2practice.translation.Translation1
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface IPostRequest {

    @POST("translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=")
    @FormUrlEncoded
    fun getCall(@Field("i") targetSentence: String): Call<Translation1>

}