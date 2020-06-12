package com.example.androidstudydemo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidstudydemo.EventBusDemo.EventBusMainActivity;
import com.example.androidstudydemo.JsonDemo.JsonActivity;
import com.example.androidstudydemo.LeakCanaryDemo.LeakActivity;
import com.example.androidstudydemo.OKHttpDemo.OkHttpActivity;
import com.example.androidstudydemo.RetrofitDemo.RetrofitActivity;
import com.example.androidstudydemo.RxjavaDemo.RxjavaMainActivity;
import com.example.androidstudydemo.ServiceDemo.ServiceActivity;
import com.example.androidstudydemo.VideoToBase64Demo.VideoToBase64Demo;
import com.example.androidstudydemo.ViewEvent.ViewEventActivity;
import com.example.androidstudydemo.VolleyDemo.VolleyActivity;
import com.example.androidstudydemo.CustomViewDemo.CustomViewActivity;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @BindView(R.id.volley_bt)
    Button volleyBt;
    @BindView(R.id.videoToBase64_bt)
    Button videoToBase64Bt;
    @BindView(R.id.service_bt)
    Button serviceBt;
    @BindView(R.id.viewEvent_bt)
    Button viewEventBt;
    @BindView(R.id.json_bt)
    Button jsonBt;
    @BindView(R.id.eventbus_bt)
    Button eventbusBt;
    @BindView(R.id.okhttp_bt)
    Button okhttpBt;
    @BindView(R.id.retrofit_bt)
    Button retrofitBt;
    @BindView(R.id.rxjava_bt)
    Button rxjavaBt;
    @BindView(R.id.leak_bt)
    Button leakBt;
    @BindView(R.id.custom_bt)
    Button customBt;
    @BindView(R.id.customView_bt)
    Button customViewBt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


    }


    @OnClick({R.id.volley_bt, R.id.videoToBase64_bt, R.id.service_bt, R.id.viewEvent_bt, R.id.json_bt, R.id.eventbus_bt, R.id.okhttp_bt, R.id.retrofit_bt, R.id.rxjava_bt, R.id.leak_bt, R.id.custom_bt, R.id.customView_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.volley_bt:
                Toast.makeText(MainActivity.this, "Volley学习", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, VolleyActivity.class);
                startActivity(intent);
                break;
            case R.id.videoToBase64_bt:
                Toast.makeText(MainActivity.this, "视屏转Base64", Toast.LENGTH_LONG).show();
                Intent intent_video = new Intent(MainActivity.this, VideoToBase64Demo.class);
                startActivity(intent_video);
                break;
            case R.id.service_bt:
                Toast.makeText(MainActivity.this, "Service学习", Toast.LENGTH_LONG).show();
                Intent intent_service = new Intent(MainActivity.this, ServiceActivity.class);
                startActivity(intent_service);
                break;
            case R.id.viewEvent_bt:
                Intent intent_viewEvent = new Intent(MainActivity.this, ViewEventActivity.class);
                startActivity(intent_viewEvent);
                break;
            case R.id.json_bt:
                Intent intent_json = new Intent(MainActivity.this, JsonActivity.class);
                startActivity(intent_json);
                break;
            case R.id.eventbus_bt:
                Intent intentEventBus = new Intent(MainActivity.this, EventBusMainActivity.class);
                startActivity(intentEventBus);
                break;
            case R.id.okhttp_bt:
                Intent intentOkHttp = new Intent(MainActivity.this, OkHttpActivity.class);
                startActivity(intentOkHttp);
                break;
            case R.id.retrofit_bt:
                Intent intentRetrofit = new Intent(MainActivity.this, RetrofitActivity.class);
                startActivity(intentRetrofit);
                break;
            case R.id.rxjava_bt:
                Intent intentRxjava = new Intent(MainActivity.this, RxjavaMainActivity.class);
                startActivity(intentRxjava);
                break;
            case R.id.leak_bt:
                Intent intentLeak = new Intent(MainActivity.this, LeakActivity.class);
                startActivity(intentLeak);
                break;
            case R.id.custom_bt:
                throw new IllegalStateException("are you ok?");
            case  R.id.customView_bt:
                Intent intentCustomView = new Intent(MainActivity.this, CustomViewActivity.class);
                startActivity(intentCustomView);
                break;

            default:
                break;
        }
    }

}
