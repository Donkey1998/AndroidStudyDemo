package com.example.androidstudydemo.ServiceDemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidstudydemo.R;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
private Button bt_serviceLife, bt_bindService, bt_blendService ,bt_intentService ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        bt_serviceLife = findViewById(R.id.bt_serviceLife);
        bt_serviceLife.setOnClickListener(this);
        bt_bindService = findViewById(R.id.bt_bindService);
        bt_bindService.setOnClickListener(this);
        bt_blendService = findViewById(R.id.bt_blendService);
        bt_blendService.setOnClickListener(this);
        bt_intentService = findViewById(R.id.bt_intentService);
        bt_intentService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_serviceLife:
                Intent intent = new Intent (this,ServiceLifeActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_bindService:
                Intent intentBind = new Intent (this,BindServiceActivity.class);
                startActivity(intentBind);
                break;
            case R.id.bt_blendService:
                Intent intentBlend = new Intent (this,BlendServiceActivity.class);
                startActivity(intentBlend);
                break;
            case R.id.bt_intentService:
                for (int i = 0; i < 10; i++) {
                    Intent intentService = new Intent(this, MyIntentService.class);
                    startService(intentService);
                }
                break;
            default:break;
        }
    }
}
