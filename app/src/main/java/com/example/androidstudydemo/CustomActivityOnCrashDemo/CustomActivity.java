package com.example.androidstudydemo.CustomActivityOnCrashDemo;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.example.androidstudydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import cat.ereza.customactivityoncrash.config.CaocConfig;

//参考链接 https://blog.csdn.net/huangxiaoguo1/article/details/79053197
public class CustomActivity extends AppCompatActivity {

    @BindView(R.id.btn_crash_restart)
    AppCompatButton btnCrashRestart;
    @BindView(R.id.btn_crash_log)
    AppCompatButton btnCrashLog;


    private CaocConfig mConfig;
    private AlertDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        ButterKnife.bind(this);
        //获得配置信息,比如设置的程序崩溃显示的页面和重新启动显示的页面等等信息
        mConfig = CustomActivityOnCrash.getConfigFromIntent(getIntent());
        if (mConfig == null) {
            // 这种情况永远不会发生，只要完成该活动就可以避免递归崩溃
            finish();
        }

    }

    @OnClick({R.id.btn_crash_restart, R.id.btn_crash_log})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_crash_restart:
//                重新打开app
                CustomActivityOnCrash.restartApplication(CustomActivity.this, mConfig);
                break;
            case R.id.btn_crash_log:
                if (mDialog == null) {
                    mDialog = new AlertDialog.Builder(CustomActivity.this)
                            .setTitle("错误详情")
                            // 获取所有的信息
                            .setMessage(CustomActivityOnCrash.getAllErrorDetailsFromIntent(CustomActivity.this, getIntent()))
                            .setPositiveButton("关闭", null)
                            .setNeutralButton("复制日志", (dialog, which) -> copyErrorToClipboard())
                            .create();
                }
                mDialog.show();
                TextView textView = mDialog.findViewById(android.R.id.message);
                if (textView != null) {
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                }
                break;
        }
    }

    /**
     * 复制报错信息到剪贴板
     */
    private void copyErrorToClipboard() {
        String errorInformation = CustomActivityOnCrash.getAllErrorDetailsFromIntent(CustomActivity.this, getIntent());
        ((ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("错误信息", errorInformation));
    }
}
