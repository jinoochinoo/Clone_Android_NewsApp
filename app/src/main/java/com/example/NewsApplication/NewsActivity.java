package com.example.NewsApplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    RequestQueue queue;

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        // 화면에 RecylerView 연결
        setContentView(R.layout.activity_news);
        mRecyclerView = findViewById(R.id.my_recycler_view);

        // row 사이즈 세팅
        mRecyclerView.setHasFixedSize(true);

        // LinearLayout 사용
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // Instantiate the RequestQueue // RequestQueue 초기화
        queue = Volley.newRequestQueue(this);
        getNews();
    }

    public void getNews(){

        String url ="";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        // Log.d("news", response);

                        try{
                            JSONObject jsonObj = new JSONObject(response);

                            JSONArray arrayArticles = jsonObj.getJSONArray("articles");

                            // NewsData 선언 및 생성 -> 분류
                            List<NewsData> news = new ArrayList<>();

                            // 반복문 통해 news 데이터 JSON 형태로 주입
                            for(int i=0, j=arrayArticles.length(); i<j; i++){
                                JSONObject obj = arrayArticles.getJSONObject(i);

                                Log.d("news", obj.toString());

                                NewsData newsData = new NewsData();
                                newsData.setTitle(obj.getString("title"));
                                newsData.setUrlToImage(obj.getString("urlToImage"));
                                newsData.setContent(obj.getString("content"));
                                news.add(newsData);
                            }

                            // adapter 세팅
                            mAdapter = new MyAdapter(news, NewsActivity.this, new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {

                                    Object obj = v.getTag();
                                    if(obj != null){
                                        int position = (int)obj; // position 값 세팅
                                        ((MyAdapter)mAdapter).getNews(position); // 어댑터에서 getNews 함수 호출

                                        // intent 활용해서 값 넘기기
                                        Intent intent = new Intent(NewsActivity.this, MyAdapter.class);
                                        intent.putExtra("news", ((MyAdapter)mAdapter).getNews(position));
                                        startActivity(intent);
                                    }
                                }
                            });
                            mRecyclerView.setAdapter(mAdapter);

                        } catch(JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

}