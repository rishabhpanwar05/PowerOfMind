package com.brahmakumari.powerofmind.model;

import java.util.List;

/**
 * Created by rishabhpanwar on 14/04/17.
 */

public class Message {
    private String message;
    private String date;
    private String imagePath;


    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date.split("T")[0];
    }

    public String getImagePath() {
        return imagePath;
    }


}
