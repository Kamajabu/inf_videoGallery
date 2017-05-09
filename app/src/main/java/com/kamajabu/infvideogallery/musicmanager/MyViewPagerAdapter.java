package com.kamajabu.infvideogallery.musicmanager;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public MyViewPagerAdapter(ArrayList<Image> images, Context context) {
        this.images = images;
        this.activityContext = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ProgressBar progressBar = null;
        String videoUrl = "http://www.ebookfrenzy.com/android_book/movie.mp4";

        layoutInflater = (LayoutInflater) activityContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.image_fullscreen_preview, container, false);

//            ImageView imageViewPreview = (ImageView) view.findViewById(R.id.image_preview);

        final VideoView videoView =
                (VideoView) view.findViewById(R.id.videoView);


        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        Uri videoUri = Uri.parse(videoUrl);
        videoView.setVideoURI(videoUri);
        videoView.start();
        progressBar.setVisibility(View.VISIBLE);
        final ProgressBar finalProgressBar = progressBar;
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // TODO Auto-generated method stub
                mp.start();
                mp.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int arg1,
                                                   int arg2) {
                        // TODO Auto-generated method stub
                        finalProgressBar.setVisibility(View.GONE);
                        mp.start();
                    }
                });
            }
        });

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