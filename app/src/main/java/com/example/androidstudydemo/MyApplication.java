package com.example.androidstudydemo;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import com.example.androidstudydemo.CustomActivityOnCrashDemo.CustomActivity;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cat.ereza.customactivityoncrash.config.CaocConfig;

public final  class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initSDK(this);
    }

    @SuppressLint("RestrictedApi")
    private   void initSDK(Application application) {

        // Crash 捕捉界面   参考链接 https://blog.csdn.net/huangxiaoguo1/article/details/79053197
        CaocConfig.Builder.create()
                //程序在后台时，发生崩溃的三种处理方式
                //BackgroundMode.BACKGROUND_MODE_SHOW_CUSTOM: //当应用程序处于后台时崩溃，也会启动错误页面，
                //BackgroundMode.BACKGROUND_MODE_CRASH:      //当应用程序处于后台崩溃时显示默认系统错误（一个系统提示的错误对话框），
                //BackgroundMode.BACKGROUND_MODE_SILENT:     //当应用程序处于后台时崩溃，默默地关闭程序！
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SHOW_CUSTOM)
                //false表示对崩溃的拦截阻止。用它来禁用customactivityoncrash框架
                .enabled(true)
                .trackActivities(true)
                // 定义应用程序崩溃之间的最短时间，以确定我们不在崩溃循环中。比如：在规定的时间内再次崩溃，框架将不处理，让系统处理！
                .minTimeBetweenCrashesMs(2000)
                // 重启的 Activity
                .restartActivity(MainActivity.class)
                // 错误的 Activity
                .errorActivity(CustomActivity.class)
                // 设置监听器
                .eventListener(new CustomEventListener())
                .apply();
        CustomActivityOnCrash.install(this);
    }

    /**
     * 监听程序崩溃/重启
     */
    private static class CustomEventListener implements CustomActivityOnCrash.EventListener {
        //程序崩溃回调
        @Override
        public void onLaunchErrorActivity() {
            Log.d("CustomEventListener", "程序崩溃回调");
        }

        //重启程序时回调
        @Override
        public void onRestartAppFromErrorActivity() {
            Log.d("CustomEventListener", "重启程序时回调");
        }

        //在崩溃提示页面关闭程序时回调
        @Override
        public void onCloseAppFromErrorActivity() {
            Log.d("CustomEventListener", "在崩溃提示页面关闭程序时回调");
        }

    }



}
