package com.brahmakumari.powerofmind.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.brahmakumari.powerofmind.R;
import com.brahmakumari.powerofmind.model.News;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ishitabhandari on 08/06/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewInfoHolder>
{
        List<News> news;
        Context ctx;
        String Url;

        public NewsAdapter(Context context, List<News> news) {
        this.ctx = context;
        this.news=news;
        }

        @Override
        public NewsAdapter.ViewInfoHolder onCreateViewHolder(ViewGroup parent, int viewType){
        final View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_news,parent,false);

        return new NewsAdapter.ViewInfoHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewInfoHolder holder, int position) {
        holder.news_title_tv.setText(news.get(position).getTitle());
        holder.news_desc_wv.loadData(news.get(position).getDescription(),"text/html;charset=utf-8","utf-8");
        holder.news_date.setText(news.get(position).getDate());
        Url=news.get(position).getImagePath();

        Picasso.with(ctx)
        .load(ctx.getString(R.string.server_url)+news.get(position).getImagePath()).resize(100,100)
        .into(holder.news_image);
        }

        @Override
        public int getItemCount() {
        return news.size();
        }

        public class ViewInfoHolder extends RecyclerView.ViewHolder
        {
        protected TextView news_title_tv,news_date;
        protected  WebView news_desc_wv;
        protected ImageView news_image;
            public ViewInfoHolder(View itemView)
            {
            super(itemView);
            news_title_tv = (TextView) itemView.findViewById(R.id.news_title);
            news_desc_wv = (WebView) itemView.findViewById(R.id.news_desc);
            news_image=(ImageView) itemView.findViewById(R.id.news_image);
            news_date = (TextView) itemView.findViewById(R.id.news_date);
             }
        }
}

