package com.example.androidstudydemo.ServiceDemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.androidstudydemo.R;

public class BlendServiceActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "BlendServiceActivity";

    private Button bt_startService, bt_stopService, bt_bindService, bt_unbindService, bt_callSeviceMethod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blend_service);
        bt_startService = findViewById(R.id.bt_startService);
        bt_startService.setOnClickListener(this);
        bt_stopService = findViewById(R.id.bt_stopService);
        bt_stopService.setOnClickListener(this);
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
            case R.id.bt_startService:
                Log.d(TAG, "start service ... ");
                Intent intentStart = new Intent(this, BlendService.class);
                startService(intentStart);
                break;
            case R.id.bindService:
                // 绑定服务
                //创建意图对象
                Intent intent = new Intent(this, BlendService.class);
                // 第一个是参数是意图对象,第二个参数是回调,第三个参数是标记,这个是自动创建的意,如果服务没有start,那么会自己创建。
                bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.bt_callSeviceMethod:
                //调用服务内部方法
                callServiceMethod();
                break;
            case R.id.unbindService:
                //解绑服务
                if (mServiceConnection != null) {
                    unbindService(mServiceConnection);
                }
                break;
            case R.id.bt_stopService:
                //停止服务
                Log.d(TAG, "stop service....");
                Intent intentStop = new Intent(this, BlendService.class);
                stopService(intentStop);
                break;

            default:break;
        }
    }

    private BlendService.CommunicateBinder mCommunicateBinder;
    private ServiceConnection mServiceConnection = new ServiceConnection(){
        //        当调用bindService方法后就会回调Activity的onServiceConnected，在这个方法中会向Activity中传递一个IBinder的实例，Acitity需要保存这个实例
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            if (service instanceof BlendService.CommunicateBinder) {
                mCommunicateBinder = (BlendService.CommunicateBinder) service;
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
