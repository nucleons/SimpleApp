package com.example.aditya.simpleapp.helper;

import android.content.Context;
import android.content.Intent;

import com.example.aditya.simpleapp.activity.DetailActivity;
import com.example.aditya.simpleapp.model.Content;

/**
 * Created by aditya on 14/9/16.
 *
 * Used for creating intents
 */
public class IntentHelper {

    public static final String MODEL = "model";

    public static Intent getDetailIntent(Content content, Context context) {
        Intent detailIntent = new Intent(context, DetailActivity.class);
        detailIntent.putExtra(MODEL, content);
        return detailIntent;
    }

    public static Content getContentFromIntent(Intent intent) {
        Content content = (Content) intent.getSerializableExtra(MODEL);
        return content;
    }
}
