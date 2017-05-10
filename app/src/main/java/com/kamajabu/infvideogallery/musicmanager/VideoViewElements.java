package com.kamajabu.infvideogallery.musicmanager;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.VideoView;

/**
 * Created by Kamil Buczel on 10.05.2017.
 */

public class VideoViewElements {
    VideoView video;
    ImageView placeholder;
    ProgressBar progressBar;

    public VideoViewElements(VideoView video, ImageView placeholder, ProgressBar progressBar) {
        this.video = video;
        this.placeholder = placeholder;
        this.progressBar = progressBar;
    }
}