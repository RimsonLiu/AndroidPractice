package com.rimson.c.remoteviewspractice

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.os.SystemClock
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.RemoteViews
import android.widget.Toast

class CustomWidgetProvider : AppWidgetProvider() {
    private val CLICK_ACTION = "com.rimson.c.remoteviewspractice.action.CLICK"

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        Log.d("hehe","onReceive")
        if (intent?.action.equals(CLICK_ACTION)) {
            Log.d("hehe","click")
            Toast.makeText(context, "clicked it", Toast.LENGTH_SHORT).show()

            Thread(Runnable {
                val bitmap = BitmapFactory.decodeResource(context!!.resources, R.mipmap.ic_launcher_round)
                val appWidgetManager = AppWidgetManager.getInstance(context)
                var i = 0
                while (i < 37) {
                    val degree = ((i * 10) % 360).toFloat()
                    val remoteViews = RemoteViews(context.packageName, R.layout.widget)
                    remoteViews.setImageViewBitmap(R.id.image_view, bitmap.rotateBitmap(degree))
                    val intentClick = Intent(context, CustomWidgetProvider::class.java)
                    intentClick.action = CLICK_ACTION
                    val pendingIntent = PendingIntent.getBroadcast(context, 0, intentClick, 0)
                    remoteViews.setOnClickPendingIntent(R.id.image_view, pendingIntent)
                    val componentName = ComponentName(context, CustomWidgetProvider::class.java)
                    val ids = appWidgetManager.getAppWidgetIds(componentName)
                    appWidgetManager.updateAppWidget(ids, remoteViews)
                    this.onUpdate(context, appWidgetManager, ids)
                    SystemClock.sleep(30)
                    i++
                }
            }).start()
            }
    }

    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager?, appWidgetIds: IntArray?) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        Log.d("hehe","onUpdate")

        if (appWidgetIds != null){
            for (i in appWidgetIds.iterator()) {
                onWidgetUpdate(context, appWidgetManager, i)
            }
        }

    }

    private fun onWidgetUpdate(context: Context?, appWidgetManager: AppWidgetManager?
            , appWidgetId: Int) {
        Log.d("hehe","onWidgetUpdate")
        val remoteViews = RemoteViews(context?.packageName, R.layout.widget)

        val intentClick = Intent(context, CustomWidgetProvider::class.java)
        intentClick.action = CLICK_ACTION
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intentClick, 0)
        remoteViews.setOnClickPendingIntent(R.id.image_view, pendingIntent)
        appWidgetManager?.updateAppWidget(appWidgetId, remoteViews)
    }

    private fun Bitmap.rotateBitmap(degree: Float): Bitmap {
        Log.d("hehe", "rotate")
        val source = this
        val matrix = Matrix()
        matrix.reset()
        matrix.setRotate(degree)
        return Bitmap.createBitmap(source, 0, 0, source.width, source.height, matrix, true)
    }
}