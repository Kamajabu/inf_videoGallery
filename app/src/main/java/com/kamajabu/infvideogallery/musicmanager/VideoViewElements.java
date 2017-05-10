package com.kamajabu.infvideogallery.musicmanager;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.VideoView;

/**
 * Created by Kamil Buczel on 10.05.2017.
 */

public class VideoViewElements {
    public VideoView video;
    public ImageView placeholder;
    public ProgressBar progressBar;
    public String videoUrl;

    public VideoViewElements(VideoView video, ImageView placeholder, ProgressBar progressBar, String videoUrl) {
        this.video = video;
        this.placeholder = placeholder;
        this.progressBar = progressBar;
        this.videoUrl = videoUrl;
    }
}