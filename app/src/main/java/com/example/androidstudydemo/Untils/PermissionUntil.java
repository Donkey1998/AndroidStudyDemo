package com.example.androidstudydemo.Untils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionUntil {

    public static final int PERMISSION_REQUEST = 1;
    private  String[] mPermissions;
    private  List<String> PermissionList = new ArrayList<>();
    private  Context mContext;

    public PermissionUntil(String[] permissions, Context context){
        this.mPermissions = permissions;
        this.mContext = context;

    }


    // 检查权限

    public  void checkPermission() {
        PermissionList.clear();

        //判断哪些权限未授予
        for (int i = 0; i < mPermissions.length; i++) {
            if (ContextCompat.checkSelfPermission(mContext, mPermissions[i]) != PackageManager.PERMISSION_GRANTED) {
                PermissionList.add(mPermissions[i]);
            }
        }
        /**
         * 判断是否为空
         */
        if (PermissionList.isEmpty()) {//未授予的权限为空，表示都授予了

        } else {//请求权限方法
            String[] mPermissions = PermissionList.toArray(new String[PermissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions((Activity) mContext, mPermissions, PERMISSION_REQUEST);
        }
    }
}
