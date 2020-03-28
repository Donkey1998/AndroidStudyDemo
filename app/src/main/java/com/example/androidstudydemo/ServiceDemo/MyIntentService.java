package com.example.androidstudydemo.ServiceDemo;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
// https://www.jianshu.com/p/be97093783d6
/*
IntentService 是继承自 Service 并处理异步请求的一个类，在 IntentService 内有一个工作线程来处理耗时操作。
当任务执行完后，IntentService 会自动停止，不需要我们去手动结束。
如果启动 IntentService 多次，那么每一个耗时操作会以工作队列的方式在 IntentService 的 onHandleIntent 回调方法中执行，依次去执行，使用串行的方式，执行完自动结束。
多遍执行完之后IntentService才会执行onDestroy方法。
 */
public class MyIntentService extends IntentService {
    private static final String TAG = MyIntentService.class.getSimpleName();

    public MyIntentService() {
        super(TAG);
    }
    private int count = 0;
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            Thread.sleep(1000);
            count ++;
            Log.w(TAG, "count::" + count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
