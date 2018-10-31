package com.bimsina.newser.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bimsina.newser.R;
import com.bimsina.newser.adapter.DownloadTask;
import com.bimsina.newser.adapter.NewsRecyclerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public String apiKey = "5bf3311dece74eae8147a7a5f009abbb";
    String apiString = "https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=5bf3311dece74eae8147a7a5f009abbb";
    String result;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    NewsRecyclerAdapter adapter;
    private ArrayList<String> newsTitles = new ArrayList<>();
    private ArrayList<String> newsDescriptions = new ArrayList<>();
    private ArrayList<String> newsPublishers = new ArrayList<>();
    private ArrayList<String> newsAuthors = new ArrayList<>();
    private ArrayList<String> urlsToNewsArticles = new ArrayList<>();
    private ArrayList<String> urlsToNewsImages = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        startProcess();
        return view;
    }
    private void startProcess() {
        DownloadTask task = new DownloadTask();
        try {
            result = task.execute(apiString).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String crappyPrefix = "null";
        if(result.startsWith(crappyPrefix)){
            result = result.substring(crappyPrefix.length(), result.length());
            //Log.i("Result","OK");
        }

        setupData();
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }
    private void setupData() {
        try {
            Log.i("Result","OK");
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
        adapter = new NewsRecyclerAdapter(getContext(),newsTitles,newsDescriptions,newsAuthors,urlsToNewsArticles,urlsToNewsImages);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


}