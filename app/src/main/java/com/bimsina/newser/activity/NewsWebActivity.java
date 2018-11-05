package com.bimsina.newser.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bimsina.newser.R;
import com.bumptech.glide.Glide;

public class NewsWebActivity extends AppCompatActivity {

    Toolbar toolbar;
    ImageView newsImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_web);

        toolbar = findViewById(R.id.webToolbar);
        newsImage = findViewById(R.id.newsImageToolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra("imageUrl");
        String title = intent.getStringExtra("title");
        String url = intent.getStringExtra("webUrl");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (imageUrl.equals("null")) {
            newsImage.setImageResource(R.drawable.splash);
        } else {
            Glide.with(this)
                    .load(imageUrl)
                    .into(newsImage);

        }

        WebView myWebView = findViewById(R.id.webView);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl(url);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home :
                finish();
                break;
        }
        return true;
    }
}
