package com.kamajabu.infvideogallery.musicmanager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.kamajabu.infvideogallery.R;
import com.kamajabu.infvideogallery.model.Image;

import java.util.ArrayList;

/**
 * Created by Kamil Buczel on 24.04.2017.
 */

public class ContentViewPagerAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private ArrayList<Image> images;
    private Context activityContext;
    private boolean isFirstElement = true;
    private VideoSlideshowContainerFragment parent;


    public VideoViewElements[] videoViewElements;


    public ContentViewPagerAdapter(ArrayList<Image> images, Context context, VideoSlideshowContainerFragment parent) {
        this.images = images;
        this.activityContext = context;
        this.videoViewElements = new VideoViewElements[images.size()];
        this.parent = parent;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) activityContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.image_fullscreen_preview, container, false);

        VideoView videoView = (VideoView) view.findViewById(R.id.videoView);
        ImageView placeholder = (ImageView) view.findViewById(R.id.placeholder);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        String videoUrl = images.get(position).getVideoUrl();

        videoViewElements[position] = new VideoViewElements(videoView, placeholder, progressBar, videoUrl);

        if (isFirstElement) {
            parent.loadAndStartVideo(videoViewElements[position]);
            isFirstElement = false;
        }

//        Bitmap as = ThumbnailUtils.createVideoThumbnail(videoUrl, MediaStore.Video.Thumbnails.MINI_KIND);

        container.addView(view);
        return view;
    }


    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}

