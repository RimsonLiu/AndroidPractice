package com.rimson.retrofit2practice.retrofit

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.rimson.retrofit2practice.R
import com.rimson.retrofit2practice.translation.Translation1
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostRequestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_request)
        request()
    }

    private fun request() {

        // 创建Retrofit对象
        val retrofit = Retrofit.Builder()
            .baseUrl("http://fanyi.youdao.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // 创建 网络请求接口 实例
        val request: IPostRequest = retrofit.create(IPostRequest::class.java)

        // 对发送请求进行封装（设置需要翻译的内容）
        val call: Call<Translation1> = request.getCall("I love you")

        // 发送网络请求（异步）
        call.enqueue(object : Callback<Translation1> {
            // 请求成功时回调
            override fun onResponse(call: Call<Translation1>, response: Response<Translation1>) {
                Log.d("hehe", "翻译是：" + response.body()?.translateResult!![0].tgt)
            }

            override fun onFailure(call: Call<Translation1>, t: Throwable) {
                Log.d("hehe", "请求失败")
                Log.d("hehe", t.message)
            }

        })
    }
}
