package com.brahmakumari.powerofmind.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import com.brahmakumari.powerofmind.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class LiveDarshanSingle extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    private static String KEY = "AIzaSyCR5kq0Y53GsZt_ByGSN79JX-H1VqAKauA";
    String videoID;
    //TextView tv_title,tv_location,tv_time,tv_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_darshan_single);

        /*tv_title= (TextView) findViewById(R.id.tv_title);
        tv_location= (TextView) findViewById(R.id.tv_location);
        tv_time= (TextView) findViewById(R.id.tv_time);
        tv_desc= (TextView) findViewById(R.id.tv_desc);*/

        Intent intent=getIntent();
        String ld_title=intent.getStringExtra("title");
        String ld_time=intent.getStringExtra("time");
        String ld_location=intent.getStringExtra("location");
        String ld_desc=intent.getStringExtra("desc");
        String ld_videoPath=intent.getStringExtra("videoPath");
        videoID=ld_videoPath.split("v=")[1];

        YouTubePlayerView youTubeView = (YouTubePlayerView)
                findViewById(R.id.videoView);
        youTubeView.initialize(KEY, this);

        /*tv_title.setText(ld_title);
        tv_location.setText(ld_location);
        tv_desc.setText(ld_desc);
        tv_time.setText(ld_time);*/

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b)
            youTubePlayer.cueVideo(videoID);

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
