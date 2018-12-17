package com.bluehomestudio.luckywheel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by mohamed on 22/04/17.
 */

public class LuckyWheel extends FrameLayout {

    private WheelView wheelView;
    private ImageView arrow;

    public LuckyWheel(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initComponent();
        applyAttribute(attrs);
    }

    public LuckyWheel(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initComponent();
        applyAttribute(attrs);
    }

    private void initComponent() {
        inflate(getContext(), R.layout.lucky_wheel_layout, this);
        wheelView = (WheelView) findViewById(R.id.wv_main_wheel);
        arrow = (ImageView) findViewById(R.id.iv_arrow);
    }

    /**
     * Function to add items to wheel items
     *
     * @param wheelItems Wheel items
     */
    public void addWheelItems(List<WheelItem> wheelItems) {
        wheelView.addWheelItems(wheelItems);
    }

    public void applyAttribute(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.LuckyWheel, 0, 0);
        try {
            int backgroundColor = typedArray.getColor(R.styleable.LuckyWheel_background_color, Color.GREEN);
            int arrowImage = typedArray.getResourceId(R.styleable.LuckyWheel_arrow_image, R.drawable.arrow);
            int rotationTime = typedArray.getResourceId(R.styleable.LuckyWheel_rotation_time, WheelView.DEFAULT_ROTATION_TIME);
            int rotations = typedArray.getResourceId(R.styleable.LuckyWheel_rotations, WheelView.DEFAULT_ROTATIONS);

            wheelView.setWheelBackgroundWheel(backgroundColor);
            arrow.setImageResource(arrowImage);
            wheelView.setRotationTime(rotationTime);
            wheelView.setRotations(rotations);

        } catch (Exception e) {
            e.printStackTrace();
        }
        typedArray.recycle();
    }

    /**
     * Function to set lucky wheel reach the target listener
     *
     * @param onLuckyWheelReachTheTarget Lucky wheel listener
     */
    public void setLuckyWheelReachTheTarget(OnLuckyWheelReachTheTarget onLuckyWheelReachTheTarget) {
        wheelView.setWheelListener(onLuckyWheelReachTheTarget);
    }

    /**
     * Function to rotate wheel to degree
     *
     * @param number Number to rotate
     */
    public void rotateWheelTo(int number) {
        wheelView.resetRotationLocationToZeroAngle(number);
    }

    /**
     * Function to retrieve wheel background color
     * @return wheel background color
     */
    public int getWheelBackgroundColor() {
        return wheelView.getWheelBackgroundColor();
    }

    /**
     * Function to set wheel background
     *
     * @param wheelBackground Wheel background color
     */
    public void setWheelBackgoundColor(int wheelBackground) {
        wheelView.setWheelBackgroundWheel(wheelBackground);
    }

    /**
     * Function to set the arrow image
     * @param resId id of the arrow resource
     */
    public void setArrowResource(int resId) {
        arrow.setImageResource(resId);
    }

    /**
     * Function to retrieve the time the spinning will take until completes
     * @return time in ms
     */
    public int getRotationTime() {
        return wheelView.getRotationTime();
    }

    /**
     * Function to set how long the spinning will take
     * @param rotationTime time of rotation in ms
     */
    public void setRotationTime(int rotationTime) {
        wheelView.setRotationTime(rotationTime);
    }

    /**
     * Function to retrieve the number of rotations that will do on each animation
     * @return number of rotations
     */
    public int getRotations() {
        return wheelView.getRotations();
    }

    /**
     * Function to set the number of rotations that the wheel will do on each animation
     * @param rotations number of rotations
     */
    public void setRotations(int rotations) {
        wheelView.setRotations(rotations);
    }
}
