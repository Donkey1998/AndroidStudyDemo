package com.example.androidstudydemo.JsonDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.androidstudydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JsonActivity extends AppCompatActivity {

    @BindView(R.id.jsonObject_bt)
    Button jsonObjectBt;
    @BindView(R.id.jsonArr_bt)
    Button jsonArrBt;
    @BindView(R.id.jsonComplex_bt)
    Button jsonComplexBt;

    private  JsonObjectDemo jsonObjectDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        ButterKnife.bind(this);
        jsonObjectDemo = new JsonObjectDemo(this);

    }

    @OnClick({R.id.jsonObject_bt, R.id.jsonArr_bt, R.id.jsonComplex_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.jsonObject_bt:
                jsonObjectDemo.JsonObject();
                break;
            case R.id.jsonArr_bt:
                jsonObjectDemo.JsonObjectArr();
                break;
            case R.id.jsonComplex_bt:
                jsonObjectDemo.JsonObjectComplex();
                break;
        }
    }
}
