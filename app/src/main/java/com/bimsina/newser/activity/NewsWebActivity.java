package com.bimsina.newser.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.bimsina.newser.R;

public class NewsWebActivity extends AppCompatActivity {

    Toolbar webToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_web);

        webToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(webToolbar);
//        if(getSupportActionBar()!=null){
//            getSupportActionBar().setTitle("");
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_search);
//        }
    }
}
