package com.kamajabu.infvideogallery.model;

import com.kamajabu.infvideogallery.R;

import java.util.ArrayList;

/**
 * Created by Kamil Buczel on 24.04.2017.
 */

public class DataLoader {

    protected static final String RES_PREFIX = "android.resource://com.kamajabu.infvideogallery/";

    static public ArrayList loadData() {
        ArrayList<Image> images = new ArrayList<>();

        images.add(new Image("cat", R.drawable.cat1, RES_PREFIX + R.raw.beam));
        images.add(new Image("Another cat", R.drawable.cat2, RES_PREFIX + R.raw.fire));
        images.add(new Image("Yet another cat", R.drawable.cat3, "android.resource://com.myapp/raw/beam.mp4"));

        images.add(new Image("Oh look! A cat.", R.drawable.cat4, "android.resource://com.myapp/raw/beam.mp4"));
        images.add(new Image("Guess what.", R.drawable.cat5, "android.resource://com.myapp/raw/beam.mp4"));
        images.add(new Image("A fish? Nah, a cat.", R.drawable.cat6, "android.resource://com.myapp/raw/beam.mp4"));

        images.add(new Image("It could be a cat.", R.drawable.food1, "android.resource://com.myapp/raw/beam.mp4"));
        images.add(new Image("Food.", R.drawable.food2, "android.resource://com.myapp/raw/beam.mp4"));
        images.add(new Image("Hungry yet?", R.drawable.food3, "android.resource://com.myapp/raw/beam.mp4"));

        images.add(new Image("Tomato potato.", R.drawable.food4, "https://player.vimeo.com/external/199224619.sd.mp4?s=801da27765a835ea41aa8b957553287d12661142"));
        images.add(new Image("Bird.", R.drawable.nature1, "https://player.vimeo.com/external/198606859.sd.mp4?s=fee252a8380077c07391b69a532710a837ae5d2e"));
        images.add(new Image("Forest", R.drawable.nature2, "https://player.vimeo.com/external/197914400.sd.mp4?s=55889f4eb38de82a28d258b71e6940d9d1c06560"));

        return images;
    }
}



