package com.example.rxjavaretrofitmvp.app;

import android.app.Application;

import com.example.rxjavaretrofitmvp.util.ActivityUtil;
import com.example.rxjavaretrofitmvp.util.LogUtil;
import com.example.rxjavaretrofitmvp.util.XUtil;


/**
 * Description : App
 *
 * @author XuCanyou666
 * @date 2020/2/8
 */


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        XUtil.initialize(this);
        //设置打印开关
        LogUtil.setIsLog(true);
        //注册Activity生命周期
        registerActivityLifecycleCallbacks(ActivityUtil.getActivityLifecycleCallbacks());
    }

}
