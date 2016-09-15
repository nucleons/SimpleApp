package com.example.aditya.simpleapp.helper;

import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by aditya on 13/9/16.
 *
 * For image loading purpose using piccasso
 */
public class ImageLoader {

    private static ImageLoader instance;

    public static ImageLoader getInstance() {
        if (instance == null) {
            synchronized (ImageLoader.class) {
                instance = new ImageLoader();
                return instance;
            }
        } else {
            return instance;
        }

    }

    private ImageLoader() {
    }

    public void loadImage(String url, ImageView im) {
        if (url != null && !url.isEmpty() && im != null) {
            Picasso picasso = Picasso.with(im.getContext());
            picasso.load(url).into(im);
        }
    }
}
