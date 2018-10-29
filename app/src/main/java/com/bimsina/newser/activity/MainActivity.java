package com.bimsina.newser.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bimsina.newser.R;
import com.bimsina.newser.adapter.DownloadTask;
import com.bimsina.newser.adapter.NewsRecyclerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public String apiKey = "5bf3311dece74eae8147a7a5f009abbb";
    String apiString = "https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=5bf3311dece74eae8147a7a5f009abbb";
    String result;
    RecyclerView recyclerView;
    NewsRecyclerAdapter adapter;
    private ArrayList<String> newsTitles = new ArrayList<>();
    private ArrayList<String> newsDescriptions = new ArrayList<>();
    private ArrayList<String> newsImages = new ArrayList<>();
    private ArrayList<String> newsPublishers = new ArrayList<>();
    private ArrayList<String> newsAuthors = new ArrayList<>();
    private ArrayList<String> urlsToNewsArticles = new ArrayList<>();
    private ArrayList<String> urlsToNewsImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.newsRecycler);
        DownloadTask task = new DownloadTask();
        try {
            result = task.execute(apiString).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String crappyPrefix = "null";
        if(result.startsWith(crappyPrefix)){
            result = result.substring(crappyPrefix.length(), result.length());
        }

        setupData();
    }

    private void setupData() {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("articles");
            for (int i =0 ; i<jsonArray.length();i++){
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                JSONArray source = jsonObject1.getJSONArray("source");
//                JSONObject name = source.getJSONObject(1);
//                Log.i("Publishers"+ i,name.getString("name"));
                newsAuthors.add(jsonObject1.getString("author"));
                newsTitles.add(jsonObject1.getString("title"));
                newsDescriptions.add(jsonObject1.getString("description"));
                urlsToNewsArticles.add(jsonObject1.getString("url"));
                urlsToNewsImages.add(jsonObject1.getString("urlToImage"));
            }
            initRecyclerView();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void initRecyclerView() {
        adapter = new NewsRecyclerAdapter(this,newsTitles,newsDescriptions,newsAuthors,urlsToNewsArticles,urlsToNewsImages);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
