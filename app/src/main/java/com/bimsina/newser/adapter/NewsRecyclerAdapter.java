package com.bimsina.newser.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bimsina.newser.R;
import com.bimsina.newser.activity.NewsWebActivity;
import com.bimsina.newser.modal_class.News;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder> {
    Context mContext;
    private List<News> newsList;
    private ArrayList<String> titleArray,authorArray,urlToArticleArray,urlToImageArray,sourceId;
    public NewsRecyclerAdapter(){}

    public NewsRecyclerAdapter(Context mContext,ArrayList<String> titleArray,
                               ArrayList<String> authorArray,ArrayList<String> urlToArticleArray,
                               ArrayList<String> urlToImageArray,ArrayList<String> sourceId){
        this.mContext = mContext;
        this.titleArray  = titleArray;
        this.authorArray = authorArray;
        this.urlToArticleArray = urlToArticleArray;
        this.urlToImageArray = urlToImageArray;
        this.sourceId = sourceId;
    }

    public NewsRecyclerAdapter(List<News> newsList){
        this.newsList = newsList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        viewHolder.setIsRecyclable(true);
        viewHolder.newsTitle.setText(newsList.get(position).getTitle());
//        viewHolder.newsDescription.setText(String.valueOf(descriptionArray.get(position)));
        viewHolder.newsAuthor.setText(newsList.get(position).getAuthor());
        String imageUrl = newsList.get(position).getUrlToImage();

        if (imageUrl.equals("null")){
            viewHolder.newsImage.setImageResource(R.mipmap.ic_launcher);
        }else {
            Glide.with(newsList.get(position).getContext())
                    .load(imageUrl)
                    .into(viewHolder.newsImage);
        }
        viewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(newsList.get(position).getContext(),NewsWebActivity.class);
                intent.putExtra("webUrl",newsList.get(position).getUrlToArticle());
                intent.putExtra("imageUrl",newsList.get(position).getUrlToImage());
                intent.putExtra("title",newsList.get(position).getTitle());
                newsList.get(position).getContext().startActivity(intent);
            }
        });
        viewHolder.menuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog myDialog;
                myDialog = new Dialog(newsList.get(position).getContext());
                myDialog.setContentView(R.layout.popup_menu);
                Window window = myDialog.getWindow();


                if (window != null) {
                    window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    window.setGravity(Gravity.BOTTOM);
                    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }
                TextView openSource = myDialog.findViewById(R.id.open_in_new_text);
                TextView openSource2 = myDialog.findViewById(R.id.open_in_new_text2);

                openSource.setText(String.valueOf(newsList.get(position).getAuthor()));
                openSource2.setText(String.valueOf(newsList.get(position).getAuthor()));
                myDialog.show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout constraintLayout;
        TextView newsTitle,newsDescription,newsAuthor;
        ImageView newsImage,menuImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsTitle = itemView.findViewById(R.id.newsTitle);
//            newsDescription = itemView.findViewById(R.id.newsDescription);
            newsAuthor = itemView.findViewById(R.id.newsAuthor);
            newsImage = itemView.findViewById(R.id.newsImage);
            menuImage = itemView.findViewById(R.id.menuImage);
            constraintLayout = itemView.findViewById(R.id.newsCard);
        }
    }
}
