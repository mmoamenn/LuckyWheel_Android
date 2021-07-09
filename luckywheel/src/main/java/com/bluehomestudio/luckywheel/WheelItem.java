package com.bluehomestudio.luckywheel;

import android.graphics.Bitmap;

/**
 * Created by mohamed on 22/04/17.
 */

public class WheelItem {

    public int id;
    public int color;
    public Bitmap bitmap;
    public String text;

    public WheelItem(int id, int color, Bitmap bitmap) {
        this.id = id;
        this.color = color;
        this.bitmap = bitmap;
    }

    public WheelItem(int id, int color, String text) {
        this.id = id;
        this.color = color;
        this.text = text;
    }

    public WheelItem(int id, int color, Bitmap bitmap, String text) {
        this.id = id;
        this.color = color;
        this.bitmap = bitmap;
        this.text = text;
    }

}
