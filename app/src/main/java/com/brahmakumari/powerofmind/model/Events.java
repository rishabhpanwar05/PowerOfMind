package com.brahmakumari.powerofmind.model;

/**
 * Created by ishitabhandari on 12/06/17.
 */

public class Events {

    private String title, venue, desc, date;

    public String getTitle() {
        return title;
    }

    public String getVenue() {
        return venue;
    }

    public String getDesc() {
        return desc;
    }
    public String getDate() {
        return date.split("T")[0];
    }



}
