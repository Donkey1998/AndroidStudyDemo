package com.example.androidstudydemo.ServiceDemo;

import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.androidstudydemo.R;

public class ServiceLifeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ServiceLifeActivity";
    private Button bt_startService, bt_stopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_life);
        bt_startService = findViewById(R.id.bt_startService);
        bt_startService.setOnClickListener(this);
        bt_stopService = findViewById(R.id.bt_stopService);
        bt_stopService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_startService:
                Log.d(TAG, "start service ... ");
                Intent intentStart = new Intent(this, ServiceLife.class);
                startService(intentStart);
                break;
            case R.id.bt_stopService:
                Log.d(TAG, "stop service....");
                Intent intentStop = new Intent(this, ServiceLife.class);
                stopService(intentStop);
                break;
            default:break;
        }
    }
}
