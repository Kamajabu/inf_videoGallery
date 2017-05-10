package com.kamajabu.infvideogallery.musicmanager;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.kamajabu.infvideogallery.R;
import com.kamajabu.infvideogallery.model.Image;

import java.util.ArrayList;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class SlideshowMusicFragment extends MusicPlayerControls
        implements MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener {

    public static SlideshowMusicFragment newInstance() {
        SlideshowMusicFragment f = new SlideshowMusicFragment();
        return f;
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow()
                .getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_music_slider, container, false);
        ButterKnife.setDebug(true);
        ButterKnife.bind(this, v);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);

        images = (ArrayList<Image>) getArguments().getSerializable("images");
        selectedPosition = getArguments().getInt("position");

        songManager = new SongsManager();
        utils = new Utilities();

        // Listeners
        songProgressBar.setOnSeekBarChangeListener(this); // Important

        // Getting all songs list
        Context playListContext = v.getContext();
        songsList = songManager.getPlayListFromContent(playListContext);

        myViewPagerAdapter = new MyViewPagerAdapter(images, getActivity());
        viewPager.setAdapter(myViewPagerAdapter);
        ViewPager.OnPageChangeListener viewPagerPageChangeListener = new VideoViewPageListener();
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        setCurrentItem(selectedPosition);

        // By default play first song
        playSong(selectedPosition);

        Log.d("Fragment", "Created");

        return v;
    }

    @OnClick(R.id.btnPlaylist)
    public void buttonPlaylistWasClicked() {

        VideoViewElements currentElements = ((MyViewPagerAdapter) viewPager.getAdapter()).videoViewElements[currentVideoIndex];

        currentElements.placeholder.addOnLayoutChangeListener((a, w, e, r, t, y, u, i, o) ->
                dismiss()
        );

        if (currentElements.placeholder.getVisibility() == GONE) {
            currentElements.placeholder.setVisibility(VISIBLE);
        } else {
            dismiss();
        }

        //
    }

    @OnClick(R.id.btnNext)
    public void buttonNextWasClicked() {
        // check if next song is there or not
        if (currentVideoIndex < (songsList.size() - 1)) {
            playSong(currentVideoIndex + 1);
            currentVideoIndex = currentVideoIndex + 1;
        } else {
            // play first song
            playSong(0);
            currentVideoIndex = 0;
        }
        viewPager.setCurrentItem(currentVideoIndex);
    }

    @OnClick(R.id.btnPrevious)
    public void buttonPreviousWasClicked() {
        if (currentVideoIndex > 0) {
            playSong(currentVideoIndex - 1);
            currentVideoIndex = currentVideoIndex - 1;
        } else {
            // play last song
            playSong(songsList.size() - 1);
            currentVideoIndex = songsList.size() - 1;
        }
        viewPager.setCurrentItem(currentVideoIndex);
    }

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100) {
            currentVideoIndex = data.getExtras().getInt("songIndex");
            // play selected song
            playSong(currentVideoIndex);
        }
    }

    public void playSong(int songIndex) {
        // Play song

    }

    public void updateProgressBar() {
        mHandler.postDelayed(mUpdateTimeTask, 100);
    }

    private Runnable mUpdateTimeTask = () -> {

    };

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        // remove message Handler from updating progress bar
        mHandler.removeCallbacks(mUpdateTimeTask);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onCompletion(MediaPlayer arg0) {

        // check for repeat is ON or OFF
        if (isRepeat) {
            // repeat is on play same song again
            playSong(currentVideoIndex);
        } else if (isShuffle) {
            // shuffle is on - play a random song
            Random rand = new Random();
            currentVideoIndex = rand.nextInt((songsList.size() - 1) - 0 + 1) + 0;
            playSong(currentVideoIndex);
        } else {
            // no repeat or shuffle ON - play next song
            if (currentVideoIndex < (songsList.size() - 1)) {
                playSong(currentVideoIndex + 1);
                currentVideoIndex = currentVideoIndex + 1;
            } else {
                // play first song
                playSong(0);
                currentVideoIndex = 0;
            }
        }
    }

    private void setCurrentItem(int position) {
        viewPager.setCurrentItem(position, false);
    }

    //	page change listener

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mHandler.removeCallbacks(mUpdateTimeTask);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
    }
}
