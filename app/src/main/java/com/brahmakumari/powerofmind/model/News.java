package com.brahmakumari.powerofmind.model;

import java.util.List;

/**
 * Created by ishitabhandari on 08/06/17.
 */

public class News {
    private String title;
    private String date;
    private String desc;
    private String imagePath;

    public String getImagePath() {
        return imagePath;
    }

    public String getDate() {
        return date.split("T")[0];
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return desc;
    }



}
