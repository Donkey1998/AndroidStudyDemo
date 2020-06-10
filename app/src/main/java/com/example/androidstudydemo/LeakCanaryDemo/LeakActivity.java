package com.example.androidstudydemo.LeakCanaryDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidstudydemo.MainActivity;
import com.example.androidstudydemo.R;

// 链接：https://juejin.im/post/5ec663ff51882543385d4524
public class LeakActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);
//        模拟耗时任务
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("=================");
                }
            }
        }).start();
    }

    public void next(View view) {
        Intent intent = new Intent(LeakActivity.this, MainActivity.class);
        startActivity(intent);
//        当Activity退出时 子线程执行耗时任务，仍然有Activity的实例  就会造成内存泄漏
        finish();
    }
}
