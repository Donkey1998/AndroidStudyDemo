package com.example.rxjavaretrofitmvp.http;

import android.util.Log;

import com.example.rxjavaretrofitmvp.http.cookie.CookiesManager;
import com.example.rxjavaretrofitmvp.http.gson.BaseConverterFactory;
import com.example.rxjavaretrofitmvp.util.XUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitService {
    private volatile static RetrofitService apiRetrofit;
    private API.WAZApi apiServer;

    /**
     * 单例调用
     *
     * @return RetrofitService
     */
    public static RetrofitService getInstance() {
        if (apiRetrofit == null) {
            synchronized (Object.class) {
                if (apiRetrofit == null) {
                    apiRetrofit = new RetrofitService();
                }
            }
        }
        return apiRetrofit;
    }

    /**
     * 获取api对象
     *
     * @return api对象
     */
    public API.WAZApi getApiService() {
        return apiServer;
    }

    /**
     * 初始化retrofit
     */
    private RetrofitService() {
        //配置okHttp并设置时间、日志信息和cookies
        //HttpLoggingInterceptor消息拦截器  https://www.jianshu.com/p/1463bc223cd8
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                try {
                    String text = URLDecoder.decode(message, "utf-8");
                    Log.e("OKHttp-----", text);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    Log.e("OKHttp-----", message);
                }
            }
        });

        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                //设置超时时间
                .connectTimeout(15, TimeUnit.SECONDS)
                //设置Cookie持久化
                .cookieJar(new CookiesManager(XUtil.getApplication()))
                .build();

        //关联okHttp并加上rxJava和Gson的配置和baseUrl
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                //  自定义Converter  https://caiyao.name/2016/01/13/Retrofit%E4%BD%BF%E7%94%A8%E4%B9%8B%E8%87%AA%E5%AE%9A%E4%B9%89Converter/
                .addConverterFactory(BaseConverterFactory.create())
                //增加RxJava适配工厂转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(API.BASE_URL)
                .build();
        apiServer = retrofit.create(API.WAZApi.class);
    }
}
