package com.bluehomestudio.luckywheel;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
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
            int backgroudnColor = typedArray.getColor(R.styleable.LuckyWheel_background_color, Color.GREEN);
            int arrowImage = typedArray.getResourceId(R.styleable.LuckyWheel_arrow_image, R.drawable.arrow);

            wheelView.setWheelBackgoundWheel(backgroudnColor);
            arrow.setImageResource(arrowImage);

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
     * Function to rotate wheel to degree
     *
     * @param number Number to rotate
     * @param interpolator custom interpolator for rotation animation
     */
    public void rotateWheelTo(int number, Interpolator interpolator) {
        wheelView.resetRotationLocationToZeroAngle(number, interpolator);
    }


    /**
     * Function to rotate wheel to degree WITHOUT animation
     *
     * @param number Number to rotate
     */
    public void rotateWheelToInstantly(int number) {
        wheelView.rotateWheelToInstantly(number);
    }

    /**
     * Function to set the wheel rotation duration
     *
     * @param timeInMilliseconds time in ms that the wheel should rotate
     */
    public void setRotationTime(int timeInMilliseconds) {
        wheelView.setRotationTime(timeInMilliseconds);
    }
}
