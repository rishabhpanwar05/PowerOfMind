package com.brahmakumari.powerofmind.model;

import java.util.List;

/**
 * Created by rishabhpanwar on 14/04/17.
 */

public class Message {
    private String message;
    private String date;
    private String imagePath;
    private List<Message> results;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public List<Message> getResults() {
        return results;
    }

    public void setResults(List<Message> results) {
        this.results = results;
    }

}
