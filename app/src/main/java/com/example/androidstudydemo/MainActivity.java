package com.example.androidstudydemo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidstudydemo.JsonDemo.JsonActivity;
import com.example.androidstudydemo.ServiceDemo.ServiceActivity;
import com.example.androidstudydemo.VideoToBase64Demo.VideoToBase64Demo;
import com.example.androidstudydemo.VolleyDemo.VolleyActivity;
import com.example.androidstudydemo.ViewEvent.ViewEventActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button volley_bt = findViewById(R.id.volley_bt);
        volley_bt.setOnClickListener(this);
        Button videoToBase64_bt = findViewById(R.id.videoToBase64_bt);
        videoToBase64_bt.setOnClickListener(this);
        Button service_bt = findViewById(R.id.service_bt);
        service_bt.setOnClickListener(this);
        Button viewEvent_bt = findViewById(R.id.viewEvent_bt);
        viewEvent_bt.setOnClickListener(this);
        Button json_bt = findViewById(R.id.json_bt);
        json_bt.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.volley_bt:
                Toast.makeText(MainActivity.this,"Volley学习",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this, VolleyActivity.class);
                startActivity(intent);
                break;
            case R.id.videoToBase64_bt:
                Toast.makeText(MainActivity.this,"视屏转Base64",Toast.LENGTH_LONG).show();
                Intent intent_video =new Intent(MainActivity.this, VideoToBase64Demo.class);
                startActivity(intent_video);
                break;
            case R.id.service_bt:
                Toast.makeText(MainActivity.this,"Service学习",Toast.LENGTH_LONG).show();
                Intent intent_service =new Intent(MainActivity.this, ServiceActivity.class);
                startActivity(intent_service);
                break;
            case R.id.viewEvent_bt:
                Intent intent_viewEvent =new Intent(MainActivity.this, ViewEventActivity.class);
                startActivity(intent_viewEvent);
                break;
            case R.id.json_bt:
                Intent intent_json =new Intent(MainActivity.this, JsonActivity.class);
                startActivity(intent_json);
                break;

            default:break;
        }
    }
}
