package com.example.androidstudydemo.ListViewDemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.androidstudydemo.R;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            list.add(Integer.valueOf(i));
        }

        ListView   listView = (ListView) findViewById(R.id.list_view);
        MyAdapter  myAdapter = new MyAdapter(this, list);
        listView.setAdapter(myAdapter);
    }
}