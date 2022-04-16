package com.example.hw51.data.models;

import com.google.gson.annotations.SerializedName;

public class Film {

    String id;
    String title;
    @SerializedName("original_title")
    String originalTitle;
    String description;
    String director;
    String producer;
    String release_date;
    String running_time;
    String rt_score;
    String movie_banner;


    public String getMovie_banner() {
        return movie_banner;
    }

    public String getDirector() {
        return director;
    }

    public String getProducer() {
        return producer;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getRunning_time() {
        return running_time;
    }

    public String getRt_score() {
        return rt_score;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getDescription() {
        return description;
    }
}
