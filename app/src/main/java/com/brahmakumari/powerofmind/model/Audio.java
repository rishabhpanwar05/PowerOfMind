package com.brahmakumari.powerofmind.model;

/**
 * Created by ishitabhandari on 18/04/17.
 */

public class Audio {
    private String title;
    private String desc;
    private String audioPath;
    private String _id;

    public String getAudio_title() {
        return title;
    }
    public void setAudio_title(String title) {
        this.title = title;
    }

    public String getAudio_desc() {
        return desc;
    }

    public void setAudio_desc(String audio_desc) {
        this.desc = audio_desc;
    }

    public String getAudio_path() {
        return audioPath;
    }

    public void setAudio_path(String audio_path) {
        this.audioPath = audio_path;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
