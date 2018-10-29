package com.bimsina.newser.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bimsina.newser.R;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder> {
    Context mContext;
    private ArrayList<String> titleArray,descriptionArray,authorArray,urlToArticleArray,urlToImageArray;
    public NewsRecyclerAdapter(){}

    public NewsRecyclerAdapter(Context mContext,ArrayList<String> titleArray,ArrayList<String> descriptionArray
            ,ArrayList<String> authorArray,ArrayList<String> urlToArticleArray,ArrayList<String> urlToImageArray){
        this.mContext = mContext;
        this.titleArray  = titleArray;
        this.descriptionArray = descriptionArray;
        this.authorArray = authorArray;
        this.urlToArticleArray = urlToArticleArray;
        this.urlToImageArray = urlToImageArray;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.newsTitle.setText(String.valueOf(titleArray.get(position)));
        viewHolder.newsDescription.setText(String.valueOf(descriptionArray.get(position)));
        viewHolder.newsAuthor.setText(String.valueOf(authorArray.get(position)));
        String imageUrl = urlToImageArray.get(position);
        Glide.with(mContext)
                .load(imageUrl).into(viewHolder.newsImage);


    }

    @Override
    public int getItemCount() {
        return titleArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView newsTitle,newsDescription,newsAuthor;
        ImageView newsImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            newsDescription = itemView.findViewById(R.id.newsDescription);
            newsAuthor = itemView.findViewById(R.id.newsAuthor);
            newsImage = itemView.findViewById(R.id.newsImage);
        }
    }
}
