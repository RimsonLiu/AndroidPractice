package com.rimson.threadpoolpractice

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private val threadPoolExecutor = ThreadPoolExecutor(3, 5, 1, TimeUnit.SECONDS, LinkedBlockingQueue<Runnable>(100))
    private val fixedThreadPoolExecutor = Executors.newFixedThreadPool(5)
    private val cachedThreadPoolExecutor = Executors.newCachedThreadPool()
    private val scheduledThreadPoolExecutor = Executors.newScheduledThreadPool(3)
    private val singleThreadPoolExecutor = Executors.newSingleThreadExecutor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createThreadPool()
        createFixedThreadPool()
        createCachedThreadPool()
        createScheduledThreadPool()
        createSingleThreadExecutor()
        shutDown()
    }

    private fun createThreadPool() {
        base_button.setOnClickListener {
            var i = 0
            while (i < 30) {
                val final = i
                val runnable = Runnable {
                    Thread.sleep(2000)
                    Log.d("Thread", "run:$final")
                }
                threadPoolExecutor.execute(runnable)
                i++
            }
        }
    }

    private fun createFixedThreadPool() {
        fixed_button.setOnClickListener {
            var i = 0
            while (i < 30) {
                val final = i
                val runnable = Runnable {
                    Thread.sleep(2000)
                    Log.d("Thread", "run:$final")
                }
                fixedThreadPoolExecutor.execute(runnable)
                i++
            }
        }
    }

    private fun createCachedThreadPool() {
        cached_button.setOnClickListener {
            var i = 0
            while (i < 30) {
                val final = i
                val runnable = Runnable {
                    Thread.sleep(2000)
                    Log.d("Thread", "run:$final")
                }
                cachedThreadPoolExecutor.execute(runnable)
                i++
            }
        }
    }

    private fun createScheduledThreadPool() {
        val runnable = Runnable {
            Log.d("Thread", "This task is delayed to execute")
        }
        scheduled_button1.setOnClickListener {
            // 延迟3s后启动任务，只执行一次
            scheduledThreadPoolExecutor.schedule(runnable, 3, TimeUnit.SECONDS)
        }
        scheduled_button2.setOnClickListener {
            // 延迟3s后启动，每隔1s执行一次（固定频率）
            scheduledThreadPoolExecutor.scheduleAtFixedRate(runnable, 3, 1, TimeUnit.SECONDS)
        }
        scheduled_button3.setOnClickListener {
            // 第一次延迟3s后执行，之后每隔1s执行一次（固定延迟）
            scheduledThreadPoolExecutor.scheduleWithFixedDelay(runnable, 3, 1, TimeUnit.SECONDS)
        }
    }

    private fun createSingleThreadExecutor() {
        single_button.setOnClickListener {
            var i = 0
            while (i < 30) {
                val final = i
                val runnable = Runnable {
                    Thread.sleep(2000)
                    Log.d("Thread", "run:$final")
                }
                singleThreadPoolExecutor.execute(runnable)
                i++
            }
        }
    }

    private fun shutDown() {
        shutdown_button.setOnClickListener {
            threadPoolExecutor.shutdown()
            fixedThreadPoolExecutor.shutdown()
            cachedThreadPoolExecutor.shutdown()
            scheduledThreadPoolExecutor.shutdown()
            singleThreadPoolExecutor.shutdown()
        }
    }

}
