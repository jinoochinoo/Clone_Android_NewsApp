package com.example.NewsApplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NewsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] mDataset;

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        // 화면에 RecylerView 연결
        setContentView(R.layout.activity_news);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // row 사이즈 세팅
        mRecyclerView.setHasFixedSize(true);

        // LinearLayout 사용
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // adapter 세팅
        mAdapter = new MyAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

    }

}