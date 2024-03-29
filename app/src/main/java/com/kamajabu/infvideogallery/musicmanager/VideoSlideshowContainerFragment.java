package com.kamajabu.infvideogallery.musicmanager;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

public class VideoSlideshowContainerFragment extends VideoPlayerControlsAbstract
        implements MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener {

    ViewPager.OnPageChangeListener viewPagerPageChangeListener;

    public static VideoSlideshowContainerFragment newInstance() {
        VideoSlideshowContainerFragment f = new VideoSlideshowContainerFragment();
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
        songProgressBar.setOnTouchListener((v1, event) -> true);
        
        // Getting all songs list
        Context playListContext = v.getContext();
        songsList = songManager.getPlayListFromContent(playListContext);

        myViewPagerAdapter = new ContentViewPagerAdapter(images, getActivity(), this);
        viewPager.setAdapter(myViewPagerAdapter);

        viewPagerPageChangeListener = new VideoViewPageListener(currentVideoIndex, viewPager, this);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
        setCurrentItem(selectedPosition);
        
        Log.d("Fragment", "Created");
    
        playerFooter.setAlpha(0.0f);

        return v;
    }
    


    public void loadAndStartVideo(VideoViewElements currentElements) {
        currentMediaPlayer = null;
        playerFooter.animate().alpha(0.0f);
        songProgressBar.animate().alpha(0.0f);
    
        currentElements.video.setVisibility(VISIBLE);

        Uri videoUri = Uri.parse(currentElements.videoUrl);

        currentElements.video.setVideoURI(videoUri);
        currentElements.progressBar.setVisibility(VISIBLE);
        currentElements.video.setOnPreparedListener(mp -> {
            currentMediaPlayer = mp;
            mp.start();
    
            //this shit is needed for avoiding making a hole in fragment before starting video
            mp.setOnVideoSizeChangedListener((mp1, arg1, arg2) -> {
                songProgressBar.animate().alpha(1.0f);
                playerFooter.animate().alpha(1.0f);
    
                btnPlay.setImageResource(R.drawable.btn_pause);
    
                currentElements.progressBar.setVisibility(GONE);
                currentElements.placeholder.setVisibility(GONE);
                updateProgressBar();
            });
        });
    }

    @OnClick(R.id.btnPlaylist)
    public void buttonPlaylistWasClicked() {

        VideoViewElements currentElements = ((ContentViewPagerAdapter) viewPager.getAdapter()).videoViewElements[currentVideoIndex];

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
    
    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            if(currentMediaPlayer!=null) {
                long totalDuration = currentMediaPlayer.getDuration();
                long currentDuration = currentMediaPlayer.getCurrentPosition();
    
                // Displaying Total Duration time
                songTotalDurationLabel.setText("" + utils.milliSecondsToTimer(totalDuration));
                // Displaying time completed playing
                songCurrentDurationLabel.setText("" + utils.milliSecondsToTimer(currentDuration));
    
                // Updating progress bar
                int progress = (int) (utils.getProgressPercentage(currentDuration, totalDuration));
                //Log.d("Progress", ""+progress);
                songProgressBar.setProgress(progress);
    
                // Running this thread after 100 milliseconds
                mHandler.postDelayed(this, 100);
            }
        }
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
            currentVideoIndex = rand.nextInt((songsList.size() - 1) + 1);
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
