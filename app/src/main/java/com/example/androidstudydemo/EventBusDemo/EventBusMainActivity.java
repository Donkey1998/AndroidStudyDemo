package com.example.androidstudydemo.EventBusDemo;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.androidstudydemo.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
// 原文链接：https://blog.csdn.net/qq_34902522/article/details/84890474
public class EventBusMainActivity extends AppCompatActivity {
    private static final String TAG = EventBusMainActivity.class.getName();
    @BindView(R.id.go_activity_bt)
    Button goActivityBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus_main);
        ButterKnife.bind(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        //注册EventBus
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onStop() {
        super.onStop();
        //取消注册
        EventBus.getDefault().unregister(this);
    }


    /* 接收事件
     * 该方法有且只有一个参数。
     * 该方法必须是public修饰符修饰，不能用static关键字修饰，不能是抽象的（abstract）
     * 该方法需要用@Subscribe注解进行修饰。
     */

     /* Subscribe是EventBus自定义的一种注解，他可接收三个参数。ThreadMode、boolean sticky、int priority。
         ThreadMode可以指定的模式有：
      *  ThreadMode.POSTING：默认的线程模式，在哪个线程发送事件就在对应线程处理事件，避免了线程切换，效率高。
      *  ThreadMode.MAIN：如在主线程（UI线程）发送事件，则直接在主线程处理事件；如果在子线程发送事件，则先将事件入队列，然后通过 Handler 切换到主线程，依次处理事件。
      *  ThreadMode.MAIN_ORDERED：无论在哪个线程发送事件，都将事件加入到队列中，然后通过Handler切换到主线程，依次处理事件。
      *  ThreadMode.BACKGROUND：与ThreadMode.MAIN相反，如果在子线程发送事件，则直接在子线程处理事件；如果在主线程上发送事件，则先将事件入队列，然后通过线程池处理事件。
      *  ThreadMode.ASYNC：与ThreadMode.MAIN_ORDERED相反，无论在哪个线程发送事件，都将事件加入到队列中，然后通过线程池执行事件
      *  链接：https://juejin.im/post/5da97188e51d4524a21c481f

         sticky
      *  sticky是一个boolean型的参数，默认值是false，表示不启用sticky特性。那么sticky特性是什么呢？
      *  我们之前说的EventBus事件传递的例子的时候，我们都是先对订阅者（Subscriber）进行先注册的，然后再post事件的。那sticky的作用是在先post事件，后对订阅者注册这种开发场景的支持的。

      *  priority
      *  该参数是int型优先级。值越高，越先接收到事件
     */

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true,priority = 1)
    public void onReceiveMsg(EventMessage eventMessage) {
        Log.i(TAG, "onReceiveMsg: " + eventMessage.toString());
    }


    @OnClick(R.id.go_activity_bt)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.go_activity_bt:
                EventPostMessage eventPostMessage = new EventPostMessage();
                eventPostMessage.postMessage();
                break;
        }

    }
}
