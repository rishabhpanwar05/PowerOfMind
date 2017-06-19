package com.brahmakumari.powerofmind.adapter;

/**
 * Created by rishabhpanwar on 02/04/17.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brahmakumari.powerofmind.R;
import com.brahmakumari.powerofmind.model.Video;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;

import java.util.List;

public class MiniVideoAdapter extends RecyclerView.Adapter<MiniVideoAdapter.VideoInfoHolder>
{

    //these ids are the unique id for each video
    Context ctx;
    List<Video> videos;
    private static String KEY = "AIzaSyCR5kq0Y53GsZt_ByGSN79JX-H1VqAKauA";

    public MiniVideoAdapter(Context context,List<Video> videos) {
        this.ctx = context;
        this.videos=videos;
    }

    @Override
    public VideoInfoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_mini_video, parent, false);
        return new VideoInfoHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final VideoInfoHolder holder, final int position) {

        holder.home_video_tv.setText(videos.get(position).getTitle());

        final YouTubeThumbnailLoader.OnThumbnailLoadedListener onThumbnailLoadedListener = new YouTubeThumbnailLoader.OnThumbnailLoadedListener() {
            @Override
            public void onThumbnailError(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader.ErrorReason errorReason) {

            }

            @Override
            public void onThumbnailLoaded(YouTubeThumbnailView youTubeThumbnailView, String s) {
                youTubeThumbnailView.setVisibility(View.VISIBLE);
                holder.relativeLayoutOverYouTubeThumbnailView.setVisibility(View.VISIBLE);
            }
        };

        holder.youTubeThumbnailView.initialize(KEY, new YouTubeThumbnailView.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {

                youTubeThumbnailLoader.setVideo(videos.get(position).getVideoPath());
                youTubeThumbnailLoader.setOnThumbnailLoadedListener(onThumbnailLoadedListener);
            }

            @Override
            public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                //write something for failure
            }
        });
    }

    @Override
    public int getItemCount() {
        int size=0;
        if (videos!=null){
            size=videos.size();
        }
        return size;
    }

    public class VideoInfoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected RelativeLayout relativeLayoutOverYouTubeThumbnailView;
        YouTubeThumbnailView youTubeThumbnailView;
        protected ImageView home_video_iv;
        protected TextView home_video_tv;

        public VideoInfoHolder(View itemView) {
            super(itemView);
            home_video_iv = (ImageView) itemView.findViewById(R.id.home_video_iv);
            home_video_tv = (TextView) itemView.findViewById(R.id.home_video_tv);
            home_video_iv.setOnClickListener(this);
            relativeLayoutOverYouTubeThumbnailView = (RelativeLayout) itemView.findViewById(R.id.relativeLayout_over_youtube_thumbnail_mini);
            youTubeThumbnailView = (YouTubeThumbnailView) itemView.findViewById(R.id.youtube_thumbnail_mini);
            youTubeThumbnailView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = YouTubeStandalonePlayer.createVideoIntent((Activity) ctx, KEY, videos.get(getLayoutPosition()).getVideoPath());
            ctx.startActivity(intent);
        }
    }
}