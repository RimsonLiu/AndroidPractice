package com.rimson.retrofit2practice.retrofit

import com.rimson.retrofit2practice.translation.Translation
import retrofit2.Call
import retrofit2.http.GET

interface IGetRequest {

    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    fun getCall(): Call<Translation>
}