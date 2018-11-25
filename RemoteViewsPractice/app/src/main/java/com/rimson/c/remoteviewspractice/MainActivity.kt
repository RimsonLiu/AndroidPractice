package com.rimson.c.remoteviewspractice

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.NotificationCompat
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notification_button.setOnClickListener { popNotification() }
    }

    private fun popNotification(){
        val mBuilder = NotificationCompat.Builder(applicationContext, "channelId")
        mBuilder.setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("我是标题")
                .setContentText("我是内容")
                .setWhen(System.currentTimeMillis())
                .setTicker("我是测试内容")
                .setDefaults(Notification.DEFAULT_VIBRATE)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "CHANNEL_ID"
            val channel =  NotificationChannel(channelId, "name", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
            mBuilder.setChannelId(channelId)
        } else {
            TODO("VERSION.SDK_INT < O")
        }

        notificationManager.notify(0, mBuilder.build())

    }
}
