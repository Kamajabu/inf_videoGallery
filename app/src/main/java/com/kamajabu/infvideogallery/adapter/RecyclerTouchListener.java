package com.kamajabu.infvideogallery.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Kamil Buczel on 24.04.2017.
 */

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    private GestureDetector gestureDetector;
    private GalleryAdapter.ClickListener clickListener;

    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final GalleryAdapter.ClickListener clickListener) {
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);

                View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && clickListener != null) {
                    clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                }

                if(e.getAction() == MotionEvent.ACTION_UP) {
                    System.out.print("UP!");
                }

            }

        });
    }


    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent event) {
        View child = rv.findChildViewUnder(event.getX(), event.getY());
        if (child != null && clickListener != null && gestureDetector.onTouchEvent(event)) {
            clickListener.onClick(child, rv.getChildPosition(child));
        }

        if(event.getAction() == MotionEvent.ACTION_UP) {
            clickListener.onClickFinish(child, rv.getChildPosition(child));
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent event) {

    }



    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
