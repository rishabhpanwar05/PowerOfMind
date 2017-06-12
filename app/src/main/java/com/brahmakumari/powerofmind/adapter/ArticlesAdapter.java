package com.brahmakumari.powerofmind.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.brahmakumari.powerofmind.R;
import com.brahmakumari.powerofmind.model.Articles;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ishitabhandari on 10/06/17.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewInfoHolder> {

    List<Articles> articles;
    Context ctx;
    String articles_url;

    public ArticlesAdapter(Context context, List<Articles> articles) {
        this.ctx = context;
        this.articles = articles;
    }

    @Override
    public ViewInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_articles,parent,false);
        return new ViewInfoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewInfoHolder holder, int position) {
        holder.articles_title_tv.setText(articles.get(position).getTitle());
        holder.articles_desc_tv.setText(articles.get(position).getDescription());
        articles_url = ctx.getString(R.string.server_url)+articles.get(position).getImagePath();

        Picasso.with(ctx)
                .load(articles_url).resize(100,100).into(holder.articles_image_iv);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }


    public class ViewInfoHolder extends RecyclerView.ViewHolder {

        protected TextView articles_title_tv, articles_desc_tv;
        protected ImageView articles_image_iv;

        public ViewInfoHolder(View itemView) {

            super(itemView);
            articles_title_tv = (TextView) itemView.findViewById(R.id.articles_title_tv);
            articles_desc_tv = (TextView) itemView.findViewById(R.id.articles_desc_tv);
            articles_image_iv = (ImageView) itemView.findViewById(R.id.articles_image_iv);

        }
    }
}
