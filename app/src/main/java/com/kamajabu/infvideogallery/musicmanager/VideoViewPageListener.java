package com.kamajabu.infvideogallery.musicmanager;

import android.net.Uri;
import android.support.v4.view.ViewPager;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Kamil Buczel on 10.05.2017.
 */

public class VideoViewPageListener extends SlideshowMusicFragment implements ViewPager.OnPageChangeListener {
    boolean positionChanged = false;
    int previousVideoIndex = 0;

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        previousVideoIndex = currentVideoIndex;
        currentVideoIndex = position;
        positionChanged = true;
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

        VideoViewElements currentElements = ((MyViewPagerAdapter) viewPager.getAdapter())
                .videoViewElements[currentVideoIndex];
        VideoViewElements previousElements = ((MyViewPagerAdapter) viewPager.getAdapter())
                .videoViewElements[previousVideoIndex];

        if (state == SCROLL_STATE_IDLE && positionChanged) {

            if (currentElements != null) {
                loadAndStartVideo(currentElements);
            }

            if (previousElements != null) {
                previousElements.video.setVisibility(GONE);
                previousElements.placeholder.setVisibility(VISIBLE);
            }

            positionChanged = false;
        }
    }

    public void loadAndStartVideo(VideoViewElements currentElements) {
        currentElements.video.setVisibility(VISIBLE);

        String videoUrl = "https://player.vimeo.com/external/212638612.sd.mp4?s=49b6d5208d2f45308b37c1fcb551ecf33b388f30";
        Uri videoUri = Uri.parse(videoUrl);

        currentElements.video.setVideoURI(videoUri);
        currentElements.progressBar.setVisibility(VISIBLE);
        currentElements.video.setOnPreparedListener(mp -> {
            mp.start();
            //this shit is needed for avoiding making a hole in fragment before starting video
            mp.setOnVideoSizeChangedListener((mp1, arg1, arg2) -> {
                currentElements.progressBar.setVisibility(GONE);
                currentElements.placeholder.setVisibility(GONE);
            });
        });
    }
}

