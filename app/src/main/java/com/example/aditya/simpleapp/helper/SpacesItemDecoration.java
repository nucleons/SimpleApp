package com.example.aditya.simpleapp.helper;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by aditya on 14/9/16.
 *
 * Used for adding spaces in recyclerview item
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int left, right, top, bottom;

    public SpacesItemDecoration(int left, int right, int top, int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.right = right;
        outRect.left = left;
        outRect.top = top;
        outRect.bottom = bottom;
    }
}
