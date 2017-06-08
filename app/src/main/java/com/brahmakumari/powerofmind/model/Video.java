package com.brahmakumari.powerofmind.model;

/**
 * Created by rishabhpanwar on 17/04/17.
 */

public class Video {

    String title;
    String videoPath;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVideoPath() {

        return videoPath.split("v=")[1];
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }
}
