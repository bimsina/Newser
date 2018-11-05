package com.bimsina.newser.modal_class;

import android.content.Context;

public class News {
    Context context;
    String title,sourceId,author,urlToArticle,urlToImage;
    public News(){}

    public News(Context context,String title,String sourceId,String author,String urlToArticle,String urlToImage){
        this.context = context;
        this.title = title;
        this.sourceId = sourceId;
        this.author = author;
        this.urlToArticle = urlToArticle;
        this.urlToImage = urlToImage;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUrlToArticle() {
        return urlToArticle;
    }

    public void setUrlToArticle(String urlToArticle) {
        this.urlToArticle = urlToArticle;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
}
