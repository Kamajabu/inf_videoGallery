package com.kamajabu.infvideogallery.musicmanager;

import android.app.DialogFragment;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.kamajabu.infvideogallery.R;
import com.kamajabu.infvideogallery.model.Image;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Kamil Buczel on 09.04.2017.
 */

public abstract class VideoPlayerControlsAbstract extends DialogFragment {

    protected ArrayList<Image> images;
    protected ViewPager viewPager;
    protected ContentViewPagerAdapter myViewPagerAdapter;
    protected int selectedPosition = 0;

    //music player fields

    // Media Player
    // Handler to update UI timer, progress bar etc,.
    protected Handler mHandler = new Handler();

    protected Utilities utils;

    protected int currentVideoIndex = 0;
    protected boolean isShuffle = false;
    protected boolean isRepeat = false;
    protected ArrayList<HashMap<String, String>> songsList = new ArrayList<>();

    @BindView(R.id.player_footer_bg)
    LinearLayout playerFooter;

    @BindView(R.id.btnPlay)
    ImageButton btnPlay;
    @BindView(R.id.btnForward)
    ImageButton btnForward;
    @BindView(R.id.btnBackward)
    ImageButton btnBackward;
    @BindView(R.id.btnNext)
    ImageButton btnNext;
    @BindView(R.id.btnPrevious)
    ImageButton btnPrevious;
    @BindView(R.id.btnPlaylist)
    ImageButton btnPlaylist;
    @BindView(R.id.btnRepeat)
    ImageButton btnRepeat;
    @BindView(R.id.btnShuffle)
    ImageButton btnShuffle;
    @BindView(R.id.songProgressBar)
    SeekBar songProgressBar;
    @BindView(R.id.songTitle)
    TextView songTitleLabel;
    @BindView(R.id.songCurrentDurationLabel)
    TextView songCurrentDurationLabel;
    @BindView(R.id.songTotalDurationLabel)
    TextView songTotalDurationLabel;
    private ImageView musicPicture;
    
    MediaPlayer currentMediaPlayer;
    
    
    @OnClick(R.id.btnPlay)
    public void playButtonWasClicked() {
        if (currentMediaPlayer == null && playerFooter.getAlpha() < 1.0f) {
            return;
        }
        // check for already playing
        //TODO refactor
        if (currentMediaPlayer.isPlaying()) {
                currentMediaPlayer.pause();
                // Changing button image to play button
                btnPlay.setImageResource(R.drawable.btn_play);
        } else {
            // Resume song
                currentMediaPlayer.start();
                // Changing button image to pause button
                btnPlay.setImageResource(R.drawable.btn_pause);
            }
        }
    
    @OnClick(R.id.btnForward)
    public void buttonForwardWasClicked() {


    }

    @OnClick(R.id.btnBackward)
    public void buttonBackwardWasClicked() {


    }




}
