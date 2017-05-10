package com.kamajabu.infvideogallery.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.kamajabu.infvideogallery.R;
import com.kamajabu.infvideogallery.model.Image;
import com.kamajabu.infvideogallery.musicmanager.VideoViewElements;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.MyViewHolder> {

    private List<Image> images;
    private Context mContext;
    private int screenHeight;
    public VideoViewElements[] videoViewElements;

    public VideoView thumbnailVideo;
    public ImageView thumbnailImage;
    public ProgressBar progressBar;


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View view) {
            super(view);
            thumbnailVideo = (VideoView) view.findViewById(R.id.thumbnailVideo);
            thumbnailImage = (ImageView) view.findViewById(R.id.thumbnailImage);
            progressBar = (ProgressBar) view.findViewById(R.id.progressbar);
        }
    }

    public GalleryAdapter(Context context, List<Image> images, int height) {
        mContext = context;
        this.images = images;
        this.screenHeight = height;
        this.videoViewElements = new VideoViewElements[images.size()];

    }
    
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                                      .inflate(R.layout.gallery_thumbnail, parent, false);

        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) itemView.getLayoutParams();
        lp.height = screenHeight / 4; //add few pixels to assure that default cells fill whole screen
        itemView.setLayoutParams(lp);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        
        Image image = images.get(position);
        String videoUrl = image.getVideoUrl();
        Drawable imageSource = mContext.getResources().getDrawable(image.getDrawable());

        thumbnailImage.setImageDrawable(imageSource);

        videoViewElements[position] = new VideoViewElements(thumbnailVideo, thumbnailImage, progressBar, videoUrl);
    }
    
    @Override
    public int getItemCount() {
        return images.size();
    }
    
    public interface ClickListener {
        void onClick(View view, int position);
        
        void onLongClick(View view, int position);

        void onClickFinish(View child, int childPosition);
    }
}