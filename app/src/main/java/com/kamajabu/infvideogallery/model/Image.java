package com.kamajabu.infvideogallery.model;

import java.io.Serializable;

public class Image implements Serializable {
    private String name;
    private int drawable;
    private String videoUrl;

    public Image() {
    }

    public Image(String name, int drawable, String videoUrl) {
        this.name = name;
        this.drawable = drawable;
        this.videoUrl = videoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
