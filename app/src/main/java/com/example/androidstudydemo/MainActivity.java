package com.example.androidstudydemo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidstudydemo.VolleyDemo.VolleyActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.volley_bt);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.volley_bt:
                Toast.makeText(MainActivity.this,"Volley学习",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(MainActivity.this, VolleyActivity.class);
                startActivity(intent);
                break;
            default:break;
        }
    }
}
