package com.example.androidstudydemo.RetrofitDemo;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface Api {


    /**
     * 1.get无参请求
     */
    @GET("article/listproject/0/json")
    Call<ResponseBody> getTab();

    /**
     * get 有参请求
     * @param map
     * @return
     */
    @GET("article/list/0/json")
    Call<RespanseBodyBean> getArticle( @QueryMap Map<String, String> map);


    /**
     *
     * @param url
     * @param map
     * @param Body
     * @return
     */
    @Headers({
            "User-Agent:android",
            "Connection:keep-alive"
    })
    @POST()
    Call<ResponseBody> postRegister(@Url String url,@QueryMap Map<String, String> map,@Body RequestBody Body);

}
