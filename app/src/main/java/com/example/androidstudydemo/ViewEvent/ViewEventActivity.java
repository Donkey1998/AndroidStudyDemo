package com.example.androidstudydemo.ViewEvent;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidstudydemo.R;

public class ViewEventActivity extends AppCompatActivity {
    protected static final String TAG = "MyButton";
    private Button mButton ;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        mButton =findViewById(R.id.id_btn);
        mButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                int action = event.getAction();

                switch (action)
                {
                    case MotionEvent.ACTION_DOWN:
                        Log.e(TAG, "OnTouchListener ACTION_DOWN");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e(TAG, "OnTouchListener ACTION_MOVE");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e(TAG, "OnTouchListener ACTION_UP");
                        break;
                    default:
                        break;
                }

                return false;
            }
        });

        mButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "onclick",Toast.LENGTH_SHORT).show();
            }
        });

        mButton.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                Toast.makeText(getApplicationContext(), "setOnLongClickListener",Toast.LENGTH_SHORT).show();
                return false;
            }
        });


    }
}
