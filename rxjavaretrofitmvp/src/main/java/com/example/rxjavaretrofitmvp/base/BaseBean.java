package com.example.rxjavaretrofitmvp.base;

public class BaseBean<T> {
    /**
     * data :
     * errorCode : 0
     * errorMsg :
     */

    public int errorCode;
    public String errorMsg;
    public T data;

    public BaseBean(int code, String data) {
        this.errorCode = code;
        this.data = (T) data;
    }
}

