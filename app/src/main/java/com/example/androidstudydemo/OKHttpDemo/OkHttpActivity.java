package com.example.androidstudydemo.OKHttpDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidstudydemo.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
//参考链接  https://www.sunofbeach.net/a/1196018104614813696
public class OkHttpActivity extends AppCompatActivity {
    private static final String TAG = OkHttpActivity.class.getName();

    @BindView(R.id.asyncGet_bt)
    Button getBt;
    @BindView(R.id.post_bt)
    Button postBt;
    @BindView(R.id.syncGet_bt)
    Button syncGetBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.asyncGet_bt, R.id.post_bt,R.id.syncGet_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.asyncGet_bt:
                asyncGet();
                break;
            case  R.id.syncGet_bt:
                 syncGet();
                 break;
            case R.id.post_bt:
                doPost();
                break;
        }
    }

    /**
     * 异步get请求
     * 1.创建OkHttpClient
     * 2.创建请求内容
     * 3.浏览器根据请求内容创建请求任务
     * 4.执行请求任务
     */
    private void asyncGet() {
        //1、创建client，理解为创建浏览器
        OkHttpClient mClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS) // 设置连接超时时间
                .build();
        //2、创建请求内容
        Request mRequest = new Request.Builder()
                .get()
                .url("http://api.k780.com/?app=weather.history&weaid=1&date=2015-07-20&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json")
                .build();
        //3、用浏览器创建调用任务
        Call call = mClient.newCall(mRequest);
        //4、执行任务
        call.enqueue(new Callback() { // 执行call

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure -- > " + e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String strByNet = response.body().string();

                // 切换到主线程
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        Toast.makeText(OkHttpActivity.this, strByNet, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    /**
     * 同步的get请求
     * 步骤跟前面一样，只是在执行任务的时候，不是异步的。
     * 同步的请求，需要自己处理线程的问题，不可以在UI线程去执行任务。
     */
    private void syncGet() {
        //1、创建client，理解为创建浏览器
        OkHttpClient mClient = new OkHttpClient.Builder()
                .connectTimeout(20, TimeUnit.SECONDS) // 设置连接超时时间
                .build();
        //2、创建请求内容
        Request mRequest = new Request.Builder()
                .get()
                .url("http://api.k780.com/?app=weather.history&weaid=1&date=2015-07-20&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json")
                .build();
        //3、用浏览器创建调用任务
        Call call = mClient.newCall(mRequest);
        //4、执行任务
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();
                    final String strByNet = response.body().string();
                     runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                            Toast.makeText(OkHttpActivity.this, strByNet, Toast.LENGTH_LONG).show();
                         }
                     });
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(TAG, "failure -- > " + e.toString());
                }
            }
        }).start();


    }

    private void doPost() {

        // 编码集
        final MediaType FORM_CONTENT_TYPE = MediaType.parse("application/json; charset=utf-8");

        // 接口地址
        final String uri = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";

        // 创建实例
        OkHttpClient okhttp = new OkHttpClient.Builder()
                .build();

        // 创建表单及数据
        HashMap<String, String> map = new HashMap<>();
//        map.put("username", username);
//        map.put("password", pass);
//        map.put("hobby", hobby);
        String jsonStr = new Gson().toJson(map);

        RequestBody formBody = RequestBody.create(FORM_CONTENT_TYPE, jsonStr);

        // 创建请求实例
        Request request = new Request.Builder()
                .url(uri)
                .post(formBody)
                .build();

        Call call = okhttp.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "接口调用失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String strByNet = response.body().string();
                // 切换到主线程
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(OkHttpActivity.this, strByNet, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }


}
