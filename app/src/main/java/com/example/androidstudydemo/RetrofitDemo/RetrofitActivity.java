package com.example.androidstudydemo.RetrofitDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.androidstudydemo.R;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//参考链接： https://blog.csdn.net/qq_36699930/article/details/80564850
public class RetrofitActivity extends AppCompatActivity {
    private static final String TAG = RetrofitActivity.class.getName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
    }
//    Retrofit2 的baseUlr 必须以 /（斜线） 结束
    public String BASE_URL = "https://www.wanandroid.com/";

//    get无参请求
    public void doGet(View view) {

        //1.创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();

        //2.通过Retrofit实例创建接口服务对象
        Api apiService = retrofit.create(Api.class);

        //3.接口服务对象调用接口中方法，获得Call对象
        Call<ResponseBody> call = apiService.getTab();

        //同步请求
        //Response<ResponseBody> bodyResponse = call.execute();

        //4.Call对象执行请求（异步、同步请求）
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                //onResponse方法是运行在主线程也就是UI线程的，所以我们可以在这里直接更新ui
                if (response.isSuccessful()) {
                    try {
                        String string =response.body().string();
                        Log.e(TAG, "onResponse: " + string);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

        //call.cancel(); //取消

    }

//   get有参请求 并且使用gson转换器直接返回对象
    public void doGetReturnObject(View view) {
        //1.创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //gson转换器
                .build();
        Map<String, String> map = new HashMap<>();
        map.put("author", "鸿洋");

        retrofit.create(Api.class)
                .getArticle(map)
                .enqueue(new Callback<RespanseBodyBean>() {
                    @Override
                    public void onResponse(Call<RespanseBodyBean> call, Response<RespanseBodyBean> response) {
                        RespanseBodyBean bodyBean = response.body();
                        List<RespanseBodyBean.DataBean.DatasBean> dataBean =bodyBean.getData().getDatas();
                        Log.e(TAG,"response "+ dataBean.size());
                    }

                    @Override
                    public void onFailure(Call<RespanseBodyBean> call, Throwable t) {

                    }
                });


    }


    public void doPost(View view){
        //1.创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //gson转换器
                .build();
        Map<String, String> map = new HashMap<>();
        map.put("username","123");
        map.put("password","123");
        map.put("repassword","123");
        String json = "";
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        retrofit.create(Api.class)
                .postRegister("user/register",map,body)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            Log.e(TAG,"response "+response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }


}
