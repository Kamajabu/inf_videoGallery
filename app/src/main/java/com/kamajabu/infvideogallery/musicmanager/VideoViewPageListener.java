package com.kamajabu.infvideogallery.musicmanager;

import android.support.v4.view.ViewPager;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Kamil Buczel on 10.05.2017.
 */

public class VideoViewPageListener implements ViewPager.OnPageChangeListener {
    boolean positionChanged = false;
    int previousVideoIndex = 0;
    protected int currentVideoIndex = 0;
    SlideshowMusicFragment parent;


    protected ViewPager viewPager;

    public VideoViewPageListener(int currentVideoIndex, ViewPager viewPager, SlideshowMusicFragment parent) {
        this.currentVideoIndex = currentVideoIndex;
        this.viewPager = viewPager;
        this.parent = parent;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        previousVideoIndex = currentVideoIndex;
        currentVideoIndex = position;
        parent.currentVideoIndex = currentVideoIndex;
        positionChanged = true;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

        VideoViewElements currentElements = ((MyViewPagerAdapter) viewPager.getAdapter())
                .videoViewElements[currentVideoIndex];
        VideoViewElements previousElements = ((MyViewPagerAdapter) viewPager.getAdapter())
                .videoViewElements[previousVideoIndex];

        if (state == SCROLL_STATE_IDLE && positionChanged) {

            if (currentElements != null) {
                parent.loadAndStartVideo(currentElements);
            }

            if (previousElements != null) {
                previousElements.video.setVisibility(GONE);
                previousElements.placeholder.setVisibility(VISIBLE);
            }

            positionChanged = false;
        }
    }
}

