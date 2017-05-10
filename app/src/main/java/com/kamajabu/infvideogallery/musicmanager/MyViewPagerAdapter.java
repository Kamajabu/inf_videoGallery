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

public class MyViewPagerAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private ArrayList<Image> images;
    private Context activityContext;


    public VideoViewElements[] videoViewElements;


    public MyViewPagerAdapter(ArrayList<Image> images, Context context) {
        this.images = images;
        this.activityContext = context;
        this.videoViewElements = new VideoViewElements[images.size()];
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) activityContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.image_fullscreen_preview, container, false);

        VideoView videoView = (VideoView) view.findViewById(R.id.videoView);
        ImageView placeholder = (ImageView) view.findViewById(R.id.placeholder);
        ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.progressbar);

        videoViewElements[position] = new VideoViewElements(videoView, placeholder, progressBar);

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

