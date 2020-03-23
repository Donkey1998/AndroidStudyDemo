package com.example.androidstudydemo.VolleyDemo;

import android.support.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

public class coustomGetStringRequest extends Request<String> {
    private Response.Listener<String> mListener;
    private Response.ErrorListener mErrorListener;

    public coustomGetStringRequest(int method, String url, Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mListener = listener;
        this.mErrorListener = errorListener;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, "utf-8"));
            return Response.success(
                    jsonString,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    //    deliverResponse(response)主要用于将得到的结果回调到主线程
    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }

    //    deliverError(error)主要用于将得到的结果回调到主线程
    @Override
    public void deliverError(VolleyError error) {
        mErrorListener.onErrorResponse(error);
    }
}
