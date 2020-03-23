package com.example.androidstudydemo.VolleyDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.androidstudydemo.R;

import java.util.HashMap;
import java.util.Map;

public class VolleyActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);
        Button bt_post = findViewById(R.id.bt_post);
        Button bt_get = findViewById(R.id.bt_get);
        Button bt_custonGet = findViewById(R.id.bt_customGet);
        bt_get.setOnClickListener(this);
        bt_post.setOnClickListener(this);
        bt_custonGet.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_get:
                getStringRequest();
                break;
            case R.id.bt_post:
                postStringRequest();
                break;
            case  R.id.bt_customGet:
                customGetRequest();
                break;
            default:
                break;
        }
    }


    private void getStringRequest() {
        // 1.创建一个请求队列
        RequestQueue mQueue = Volley.newRequestQueue(this);
        // 2.创建一个请求
        String url = "http://api.k780.com/?app=weather.history&weaid=1&date=2015-07-20&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
           new Response.Listener<String>() { //正确的返回数据
            @Override
            public void onResponse(String response) {
                Toast.makeText(VolleyActivity.this, response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() { // 错误的返回数据
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VolleyActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }
        );
        // 3.将创建的请求添加到请求队列中 
        mQueue.add(stringRequest);
    }

    private void  postStringRequest(){
        // 1 创建一个请求队列
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // 2 创建一个post请求
        String url = "http://api.m.mtime.cn/PageSubArea/TrailerList.api";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(VolleyActivity.this, response, Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VolleyActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
//                        map.put("value1","param1");
                return map;
            }
        };

        // 3 将post请求添加到队列中
        requestQueue.add(stringRequest);

    }

   //自定义Get请求
    private void customGetRequest() {
        String url = "http://api.k780.com/?app=weather.history&weaid=1&date=2015-07-20&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
        coustomGetStringRequest coustomGetStringRequest = new coustomGetStringRequest (Request.Method.GET, url,
                new Response.Listener<String>() { //正确的返回数据
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(VolleyActivity.this, response, Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() { // 错误的返回数据
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VolleyActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue rQueue;
        coustomGetStringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rQueue = Volley.newRequestQueue(this);
        rQueue.add(coustomGetStringRequest);
    }

}
