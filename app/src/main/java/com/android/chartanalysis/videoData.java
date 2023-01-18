package com.android.chartanalysis;

public class videoData {

    String id,title,url,thumbnail;

    public videoData(String id, String title, String url, String thumbnail) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnail(){return thumbnail;}
}
