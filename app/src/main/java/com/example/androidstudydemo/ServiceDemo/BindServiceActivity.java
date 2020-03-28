package com.example.androidstudydemo.ServiceDemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidstudydemo.R;

/***
 * 绑定服务的特点
 *
 * 1、bindService开启的服务，在系统里是看不到服务在运行的;通过startService的方式启动的服务，则会在系统里看到
 *
 * 2、如果onBind方法返回的是null,那么onServiceConnected方法不会被调用;
 *
 * 3、绑定服务的生命周期跟Activity是不求同时生，但求同时死，Activity没了，服务也要解绑；
 *
 * 4、服务在解除绑定以后会停止运行，执行unBind方法—>onDestroy方法；
 *
 * 5、绑定服务开启的服务，只可以解绑一次，多次解绑会抛异常；
 *
 * 6、绑定的connection要跟解绑的connection要对应着，否则没法解绑。
 *
 * 稍微总结一下，startService和bindService的区别，优点和缺点：
 *
 * 1、startService这个方法来启动服务的话，是长期运行的，只有stopService才会停止服务。而bindService来启动服务，不用的时候，需要调用unBindService，否则会导致context泄漏，所以bindService不是长期运行的。当context销毁的时候，则会停止服务运行。
 *
 * 2、startService来启动服务可以长期运行，但是不可以通讯，而bindService的方式来启动服务则可以通讯，两者都有优缺点，所以我们就有了混合起来使用的方法。
 */

public class BindServiceActivity extends AppCompatActivity implements View.OnClickListener {
    private Button bt_bindService, bt_unbindService, bt_callSeviceMethod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service);
        bt_bindService = findViewById(R.id.bindService);
        bt_bindService.setOnClickListener(this);
        bt_unbindService = findViewById(R.id.unbindService);
        bt_unbindService.setOnClickListener(this);
        bt_callSeviceMethod = findViewById(R.id.bt_callSeviceMethod);
        bt_callSeviceMethod.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bindService:
                //创建意图对象
                Intent intent = new Intent(this, BindService.class);
                // 第一个是参数是意图对象,第二个参数是回调,第三个参数是标记,这个是自动创建的意,如果服务没有start,那么会自己创建。
                bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.unbindService:
                //解绑服务
                if (mServiceConnection != null) {
                    unbindService(mServiceConnection);
                }
                break;
            case R.id.bt_callSeviceMethod:
                callServiceMethod();
                break;
            default:break;
        }
    }

    private BindService.CommunicateBinder mCommunicateBinder;
    private ServiceConnection  mServiceConnection = new ServiceConnection(){
//        当调用bindService方法后就会回调Activity的onServiceConnected，在这个方法中会向Activity中传递一个IBinder的实例，Acitity需要保存这个实例
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            if (service instanceof BindService.CommunicateBinder) {
                mCommunicateBinder = (BindService.CommunicateBinder) service;
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    public void callServiceMethod() {
        if (mCommunicateBinder != null) {
            //调用服务内部的方法
            mCommunicateBinder.callInnerMethod();
        }
    }

}
