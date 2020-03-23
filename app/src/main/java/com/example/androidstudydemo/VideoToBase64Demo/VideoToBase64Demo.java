package com.example.androidstudydemo.VideoToBase64Demo;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.androidstudydemo.R;
import com.example.androidstudydemo.Untils.PermissionUntil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class VideoToBase64Demo extends AppCompatActivity implements View.OnClickListener {
    private static  final String TAG = "VideoToBase64Demo";
   private  String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_to_base64_demo);

        PermissionUntil permissionUntil = new PermissionUntil(permissions,this);
        permissionUntil.checkPermission();

        Button bt_getVideo = findViewById(R.id.bt_getVideo);
        bt_getVideo.setOnClickListener(this);
        Button bt_videoToBase64 = findViewById(R.id.bt_videoToBase64);
        bt_videoToBase64.setOnClickListener(this);
        Button bt_base64ToVideo = findViewById(R.id.bt_base64ToVideo);
        bt_base64ToVideo.setOnClickListener(this);
    }
   private String baseStr;
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_getVideo:
//                选择视屏
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("video/*");
                startActivityForResult(intent,1);
                break;
            case R.id.bt_videoToBase64:
//                视屏文件转Base64
                baseStr = fileBase64String(uri);
                Log.d(TAG,"视屏文件Base64  "+ baseStr);
                break;
            case R.id.bt_base64ToVideo:
//                base64转视屏
                base64ToVideo(baseStr);
                break;
            default:break;
        }
    }

    private  Uri uri;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            uri = data.getData();
            Log.d(TAG,"视屏文件地址  "+ uri.toString());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 图片文件转Base64字符串
     * @param
     * @return
     */
    private String fileBase64String(Uri url){
        try {
            InputStream fis = getContentResolver().openInputStream(url);;//转换成输入流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count = 0;
            while((count = fis.read(buffer)) >= 0){
                baos.write(buffer, 0, count);//读取输入流并写入输出字节流中
            }
            fis.close();//关闭文件输入流
            return Base64.encodeToString(baos.toByteArray(),Base64.DEFAULT);
        } catch (Exception e) {
            Log.e(TAG, "错误--> " + e);
            return null;
        }

    }

    /**
     * base64 视频base64字符串
     * videoFilePath  输出视频文件路径带文件名
     * https://www.cnblogs.com/Small-sunshine/p/12482198.html
     */
    public static void base64ToVideo(String base64) {
        try {
            //base解密
            byte[] videoByte = Base64.decode(base64.getBytes(),Base64.DEFAULT);
            File videoFile = new File(Environment.getExternalStorageDirectory()
                    + "/Convert.mp4");
            if (videoFile.exists()){
                videoFile.delete();
            }
            try {
                //创建文件
                videoFile.createNewFile();

            } catch (IOException e) {
                e.printStackTrace();
                Log.e("creatXMLFileException",e.getMessage());
            }

            //输入视频文件
            FileOutputStream fos = new FileOutputStream(videoFile);
            fos.write(videoByte, 0, videoByte.length);
            fos.flush();
            fos.close();
            Log.d(TAG,"视屏保存的地址--" + videoFile);
        } catch (IOException e) {
            Log.e(TAG,"base64转换为视频异常",e);
        }
    }


    /**
     * 响应授权
     * 这里不管用户是否拒绝，都进入首页，不再重复申请权限
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] mPermissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, mPermissions, grantResults);
        switch (requestCode) {
            case PermissionUntil.PERMISSION_REQUEST:

                break;
            default:
                super.onRequestPermissionsResult(requestCode, mPermissions, grantResults);
                break;
        }
    }
}
