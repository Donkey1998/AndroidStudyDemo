package com.example.androidstudydemo.GlideDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.androidstudydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.security.AccessController.getContext;

public class GlideDemoActivity extends AppCompatActivity {

    @BindView(R.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_demo);
        ButterKnife.bind(this);
    }

    public void getImage(View view) {
        Glide.with(this)
                .load("http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg")
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
                .into(imageView);
    }
}