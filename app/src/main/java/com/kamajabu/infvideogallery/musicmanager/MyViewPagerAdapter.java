package com.kamajabu.infvideogallery.musicmanager;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.kamajabu.infvideogallery.R;
import com.kamajabu.infvideogallery.model.Image;

import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Kamil Buczel on 24.04.2017.
 */

public class MyViewPagerAdapter extends PagerAdapter {

    private LayoutInflater layoutInflater;
    private ArrayList<Image> images;
    private Context activityContext;

    public VideoView[] arrayOfPlayers;
    public View[] arrayOfPlaceholders;



    public MyViewPagerAdapter(ArrayList<Image> images, Context context) {
        this.images = images;
        this.activityContext = context;
        arrayOfPlayers = new VideoView[images.size()];
        arrayOfPlaceholders = new View[images.size()];
    }



    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ProgressBar progressBar = null;
        String videoUrl = "https://player.vimeo.com/external/212638612.sd.mp4?s=49b6d5208d2f45308b37c1fcb551ecf33b388f30";

        layoutInflater = (LayoutInflater) activityContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.image_fullscreen_preview, container, false);

//            ImageView imageViewPreview = (ImageView) view.findViewById(R.id.image_preview);
        VideoView videoView;
        View placeholder;

        videoView = (VideoView) view.findViewById(R.id.videoView);
        placeholder = view.findViewById(R.id.placeholder);

        arrayOfPlayers[position] = videoView;
        arrayOfPlaceholders[position] = placeholder;

        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        Uri videoUri = Uri.parse(videoUrl);

        videoView.setVideoURI(videoUri);

        final ProgressBar finalProgressBar = progressBar;
        ProgressBar finalProgressBar1 = progressBar;

//        videoView.start();

        videoView.setOnPreparedListener(mp -> {

            finalProgressBar1.setVisibility(VISIBLE);
            mp.start();
            mp.setOnVideoSizeChangedListener((mp1, arg1, arg2) -> {
                // TODO Auto-generated method stub
            placeholder.setVisibility(GONE);
                finalProgressBar.setVisibility(GONE);

//                mp1.start();
            });
        });


        container.addView(view);

        Log.d("Adapter", "Inistiated");
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

    public void clear() {
//        videoView.setVisibility(GONE);
//        videoView.setVisibility(VISIBLE);
//        placeholder.setVisibility(VISIBLE);
    }
}