package com.kamajabu.infvideogallery.model;

import com.kamajabu.infvideogallery.R;

import java.util.ArrayList;

/**
 * Created by Kamil Buczel on 24.04.2017.
 */

public class DataLoader {

    static public ArrayList loadData() {
        ArrayList<Image> images = new ArrayList<>();

        images.add(new Image("cat", R.drawable.cat1, "https://player.vimeo.com/external/212638612.sd.mp4?s=49b6d5208d2f45308b37c1fcb551ecf33b388f30"));
        images.add(new Image("Another cat", R.drawable.cat2, "https://player.vimeo.com/external/211655655.sd.mp4?s=f9985a81b298ae3f786b2c2c133910a1ab5c291e"));
        images.add(new Image("Yet another cat", R.drawable.cat3, "https://player.vimeo.com/external/196282555.sd.mp4?s=71485a654e0ac9d1389d1d1c8abe0213be7777d9"));

        images.add(new Image("Oh look! A cat.", R.drawable.cat4, "https://player.vimeo.com/external/209165711.sd.mp4?s=299b5dc333f6e6fbc77b094715e39c9977e36888"));
        images.add(new Image("Guess what.", R.drawable.cat5, "https://player.vimeo.com/external/207209033.hd.mp4?s=ea975e5ac2a43ff2038d737e0c95c5b11268ae0a"));
        images.add(new Image("A fish? Nah, a cat.", R.drawable.cat6, "https://player.vimeo.com/external/204506406.sd.mp4?s=68b7b8523b4cb8fa0690082bdd63302813f37ebd"));

        images.add(new Image("It could be a cat.", R.drawable.food1, "https://player.vimeo.com/external/201447783.sd.mp4?s=130d8f7819c7535b49095089c9ba96d451615ff1"));
        images.add(new Image("Food.", R.drawable.food2, "https://player.vimeo.com/external/200092486.sd.mp4?s=613baf0e2aaeb3cd49fcc342b3fbb12f71a3acaf"));
        images.add(new Image("Hungry yet?", R.drawable.food3, "https://player.vimeo.com/external/199627560.sd.mp4?s=4d51ea25ca083b46834911fc794db2e99e6075c7"));

        images.add(new Image("Tomato potato.", R.drawable.food4, "https://player.vimeo.com/external/199224619.sd.mp4?s=801da27765a835ea41aa8b957553287d12661142"));
        images.add(new Image("Bird.", R.drawable.nature1, "https://player.vimeo.com/external/198606859.sd.mp4?s=fee252a8380077c07391b69a532710a837ae5d2e"));
        images.add(new Image("Forest", R.drawable.nature2, "https://player.vimeo.com/external/197914400.sd.mp4?s=55889f4eb38de82a28d258b71e6940d9d1c06560"));

        return images;
    }
}



