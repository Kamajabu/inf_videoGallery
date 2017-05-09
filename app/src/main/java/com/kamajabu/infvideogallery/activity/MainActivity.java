package com.kamajabu.infvideogallery.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

import com.kamajabu.infvideogallery.R;
import com.kamajabu.infvideogallery.adapter.GalleryAdapter;
import com.kamajabu.infvideogallery.adapter.RecyclerTouchListener;
import com.kamajabu.infvideogallery.model.DataLoader;
import com.kamajabu.infvideogallery.musicmanager.SlideshowMusicFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class MainActivity extends Activity {

    private ArrayList images;
    private GalleryAdapter mAdapter;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private RecyclerView.OnScrollListener scrollListener;
    private  GalleryAdapter.ClickListener galleryListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        images = DataLoader.loadData();

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;

        mAdapter = new GalleryAdapter(getApplicationContext(), images, height);
        prepareRecyclerView();
        createListeners();

        mAdapter.notifyDataSetChanged();
    }

    private void createListeners() {
        createScrollListener();
        createGalleryListener();

        recyclerView.addOnScrollListener(scrollListener);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                recyclerView, galleryListener));
    }

    private void prepareRecyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    private void createGalleryListener() {
        galleryListener = new GalleryAdapter.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                goToFullScreenPreview(position);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        };
    }

    private void createScrollListener() {
        scrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int state) {
                super.onScrollStateChanged(recyclerView, state);
                alignToFullyVisibleCell(recyclerView, state);
            }
        };
    }

    private void alignToFullyVisibleCell(RecyclerView recyclerView, int state) {
        if (state == SCROLL_STATE_IDLE) {
            GridLayoutManager glm = ((GridLayoutManager) recyclerView.getLayoutManager());
            int lastVisiblePosition = glm.findLastVisibleItemPosition();
            recyclerView.smoothScrollToPosition(lastVisiblePosition);
        }
    }

    private void goToFullScreenPreview(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("images", images);
        bundle.putInt("position", position);

        android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.animator.anim_slide_in_top, R.animator.anim_slide_out_bottom);

        SlideshowMusicFragment newFragment = SlideshowMusicFragment.newInstance();
        newFragment.setCancelable(false);
        newFragment.setArguments(bundle);
        newFragment.show(ft, "slideshow");
    }
}