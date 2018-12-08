package com.rimson.retrofit2practice.retrofit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.rimson.retrofit2practice.R
import com.rimson.retrofit2practice.translation.Translation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GetRequestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_request)
        request()
    }

    private fun request() {
        // 创建Retrofit对象
        val retrofit = Retrofit.Builder()
            .baseUrl("http://fy.iciba.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // 创建 网络请求接口 实例
        val request: IGetRequest = retrofit.create(IGetRequest::class.java)

        // 封装发送请求
        val call: Call<Translation> = request.getCall()

        // 发送网络请求（异步）
        call.enqueue(object : Callback<Translation> {
            // 请求成功时回调
            override fun onResponse(call: Call<Translation>, response: Response<Translation>) {
                // 请求处理，输出结果
                response.body()?.show()
            }

            // 请求失败时回调
            override fun onFailure(call: Call<Translation>, t: Throwable) {
                Log.d("hehe", "连接失败")
            }
        })
    }

}
