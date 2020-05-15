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
    @BindView(R.id.gsonObject_bt)
    Button gsonObjectBt;
    @BindView(R.id.gsonArr_bt)
    Button gsonArrBt;
    @BindView(R.id.gsonComplex_bt)
    Button gsonComplexBt;
    @BindView(R.id.fastJsonObject_bt)
    Button fastJsonObjectBt;
    @BindView(R.id.fastJsonArr_bt)
    Button fastJsonArrBt;
    @BindView(R.id.fastJsonComplex_bt)
    Button fastJsonComplexBt;

    private JsonObjectDemo jsonObjectDemo;
    private GsonDemo gsonDemo;
    private FastJsonDemo fastJsonDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        ButterKnife.bind(this);
        jsonObjectDemo = new JsonObjectDemo(this);
        gsonDemo = new GsonDemo(this);
        fastJsonDemo = new FastJsonDemo(this);

    }

    @OnClick({R.id.jsonObject_bt, R.id.jsonArr_bt, R.id.jsonComplex_bt,R.id.gsonObject_bt,R.id.gsonArr_bt,R.id.gsonComplex_bt,R.id.fastJsonObject_bt,R.id.fastJsonArr_bt,R.id.fastJsonComplex_bt})
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
            case R.id.gsonObject_bt:
                gsonDemo.GsonObject();
                break;
            case R.id.gsonArr_bt:
                gsonDemo.GsonObjectArr();
                break;
            case R.id.gsonComplex_bt:
                gsonDemo.GsonObjectComplex();
                break;
            case R.id.fastJsonObject_bt:
                fastJsonDemo.fastJsonObject();
                break;
            case R.id.fastJsonArr_bt:
                fastJsonDemo.fastJsonArr();
                break;
            case R.id.fastJsonComplex_bt:
                fastJsonDemo.fastJsonComplex();
                break;
        }
    }
}
