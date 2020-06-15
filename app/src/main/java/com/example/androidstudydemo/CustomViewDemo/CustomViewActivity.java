package com.example.androidstudydemo.CustomViewDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.androidstudydemo.R;

public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        CustomTitleView  mTitleBar= (CustomTitleView) this.findViewById(R.id.title);
//      mTitleBar.setTitle("自定义组合控件");

        mTitleBar.setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomViewActivity.this, "点击左键", Toast.LENGTH_SHORT).show();
            }
        });

        mTitleBar.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CustomViewActivity.this, "点击右键", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

