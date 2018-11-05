package com.bimsina.newser.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
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
import com.bimsina.newser.modal_class.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TrendingFragment extends Fragment {

    public String apiKey = "5bf3311dece74eae8147a7a5f009abbb";
    String apiString = "https://newsapi.org/v2/top-headlines?country=us&apiKey=5bf3311dece74eae8147a7a5f009abbb";
    String result,newsTitle,sourceId,author,urlToArticle,urlToImge;
    RecyclerView recyclerView;
    NewsRecyclerAdapter adapter;
    List<News> newsList;
    ConstraintLayout errorView;
    JSONArray jsonArray;
    Integer totalNewsArticles,lastKnownNews=0;
    ProgressBar progressBar;
//    private ArrayList<String> newsTitles = new ArrayList<>();
//    private ArrayList<String> newsDescriptions = new ArrayList<>();
//    private ArrayList<String> newsSourceId = new ArrayList<>();
//    private ArrayList<String> newsAuthors = new ArrayList<>();
//    private ArrayList<String> urlsToNewsArticles = new ArrayList<>();
//    private ArrayList<String> urlsToNewsImages = new ArrayList<>();

    public TrendingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter = new NewsRecyclerAdapter(newsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
        startProcess();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_trending, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        errorView = view.findViewById(R.id.errorView);
        progressBar = view.findViewById(R.id.progressNews);
        newsList = new ArrayList<>();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void startProcess() {
        DownloadTask task = new DownloadTask();
        try {
            result = task.execute(apiString).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!result.equals("failed")) {
            String crappyPrefix = "null";
            if (result.startsWith(crappyPrefix)) {
                result = result.substring(crappyPrefix.length(), result.length());
                //Log.i("Result","OK");
            }
            setupData();
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }else {
            errorView.setVisibility(View.VISIBLE);
        }

    }
    private void setupData() {
        try {
            Log.i("Result","OK");
            JSONObject jsonObject = new JSONObject(result);
            jsonArray = jsonObject.getJSONArray("articles");
            totalNewsArticles = jsonArray.length();
            loadMoreNews(0);
//            if(totalNewsArticles > 5 ) {
//                loadMoreNews(5);
//            }
//            else {
//                loadMoreNews(totalNewsArticles);
//            }
//            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//                @Override
//                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                    super.onScrolled(recyclerView, dx, dy);
//
//                    Boolean reachedBottom = !recyclerView.canScrollVertically(1);
//                    if (reachedBottom) {
//                        progressBar.setProgress(View.VISIBLE);
//                        loadMoreNews(lastKnownNews + 3);
//                    }
//                }
//            });

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void loadMoreNews(int length) {
//        int temp = lastKnownNews;
//        lastKnownNews = length;

        for ( int i = 0 ; i < totalNewsArticles ; i++){

            try {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                JSONObject source = jsonObject1.getJSONObject("source");
                newsTitle = jsonObject1.getString("title");
                sourceId = source.getString("id");
                author = source.getString("name");
                urlToArticle =jsonObject1.getString("url");
                urlToImge = jsonObject1.getString("urlToImage");
                News news = new News(getContext(),newsTitle,sourceId,author,urlToArticle,urlToImge);
                newsList.add(news);
                adapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }



}
