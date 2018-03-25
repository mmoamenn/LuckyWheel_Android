package com.bluehomestudio.luckywheel;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;

import java.util.List;

/**
 * Created by mohamed on 22/04/17.
 */

public class WheelView extends View {
    private final int DEFAULT_PADDING = 5, DEFAULT_ROTATION_TIME = 9000;

    private int mRotationTime = DEFAULT_ROTATION_TIME;
    private boolean mTextRotationHorizontal = false;

    private RectF range = new RectF();
    private Rect textBounds = new Rect();
    private Paint archPaint;
    private Paint textPaint;
    private int padding, radius, center, mWheelBackground;
    private List<WheelItem> mWheelItems;
    private OnLuckyWheelReachTheTarget mOnLuckyWheelReachTheTarget;

    public WheelView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WheelView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initComponents() {
        //arc paint object
        archPaint = new Paint();
        archPaint.setAntiAlias(true);
        archPaint.setDither(true);
        // text paint
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.LEFT);
        //rect rang of the arc
        range = new RectF(padding, padding, padding + radius, padding + radius);
    }

    /**
     * Get the angele of the target
     *
     * @return Number of angle
     */
    private float getAngleOfIndexTarget(int target) {
        return (360 / mWheelItems.size()) * target;
    }

    /**
     * Function to set wheel background
     *
     * @param wheelBackground Wheel background color
     */
    public void setWheelBackgoundWheel(int wheelBackground) {
        mWheelBackground = wheelBackground;
        invalidate();
    }

    /**
     * Function to set wheel listener
     *
     * @param onLuckyWheelReachTheTarget target reach listener
     */
    public void setWheelListener(OnLuckyWheelReachTheTarget onLuckyWheelReachTheTarget) {
        mOnLuckyWheelReachTheTarget = onLuckyWheelReachTheTarget;
    }

    /**
     * Function to add wheels items
     *
     * @param wheelItems Wheels model item
     */
    public void addWheelItems(List<WheelItem> wheelItems) {
        mWheelItems = wheelItems;
        invalidate();
    }

    /**
     * Function to set the wheel rotation duration
     *
     * @param timeInMilliseconds time in ms that the wheel should rotate
     */
    public void setRotationTime(int timeInMilliseconds) {
        mRotationTime = timeInMilliseconds;
    }

    /**
     * Function to set the text rotation to be horizontal instead of vertical
     *
     * @param horizontal true if text should be drawn horizontal
     */
    public void setTextRotationHorizontally(boolean horizontal) {
        mTextRotationHorizontal = horizontal;
    }

    /**
     * Function to draw wheel background
     *
     * @param canvas Canvas of draw
     */
    private void drawWheelBackground(Canvas canvas) {
        Paint backgroundPainter = new Paint();
        backgroundPainter.setAntiAlias(true);
        backgroundPainter.setDither(true);
        backgroundPainter.setColor(mWheelBackground);
        canvas.drawCircle(center, center, center, backgroundPainter);
    }

    /**
     * Function to draw image in the center of arc
     *
     * @param canvas    Canvas to draw
     * @param tempAngle Temporary angle
     * @param bitmap    Bitmap to draw
     */
    private int drawImage(Canvas canvas, float tempAngle, Bitmap bitmap) {
        //get every arc img width and angle
        int imgWidth = radius / mWheelItems.size();
        float angle = (float) ((tempAngle + 360 / mWheelItems.size() / 2) * Math.PI / 180);
        //calculate x and y
        int x = (int) (center + radius / 2 / 2 * Math.cos(angle));
        int y = (int) (center + radius / 2 / 2 * Math.sin(angle));
        //create arc to draw
        Rect rect = new Rect(x - imgWidth / 2, y - imgWidth / 2, x + imgWidth / 2, y + imgWidth / 2);
        //rotate main bitmap
        Matrix matrix = new Matrix();
        matrix.postRotate(45);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        canvas.drawBitmap(rotatedBitmap, null, rect, null);
        return imgWidth;
    }

    private void drawText(int imgWidth, Canvas canvas, float tempAngle, String text, int color, int size) {
        float centerArcAngle = tempAngle + 360 / mWheelItems.size() / 2;
        float angleInRad = (float) Math.toRadians(centerArcAngle);
        //calculate x and y (center in arc)
        int x = (int) (center + radius / 2 / 2 * Math.cos(angleInRad));
        int y = (int) (center + radius / 2 / 2 * Math.sin(angleInRad));
        // update paint
        textPaint.setTextSize(size);
        textPaint.setColor(color);
        // draw text - move it to the end of the image + move it down half of the text size to be centered on the text's y axis
        textPaint.getTextBounds(text, 0, text.length(), textBounds);
        canvas.translate(x, y);
        canvas.rotate(centerArcAngle);
        if (mTextRotationHorizontal) {
            canvas.translate(imgWidth / 2, 0);
            canvas.rotate(90);
            canvas.drawText(text, imgWidth / 2, -textBounds.exactCenterY(), textPaint);
            canvas.translate(-imgWidth / 2, 0);
            canvas.rotate(-90);
        } else {
            canvas.drawText(text, imgWidth / 2, -textBounds.exactCenterY(), textPaint);
        }
        canvas.rotate(-centerArcAngle);
        canvas.translate(-x, -y);
    }

    /**
     * Function to rotate wheel to target
     *
     * @param target target number
     */
    public void rotateWheelToTarget(int target) {
        rotateWheelToTarget(target, new DecelerateInterpolator());
    }

    /**
     * Function to rotate wheel to target
     *
     * @param target       target number
     * @param interpolator custom interpolator for rotation animation
     */
    public void rotateWheelToTarget(int target, Interpolator interpolator) {

        float wheelItemCenter = 270 - getAngleOfIndexTarget(target) + (360 / mWheelItems.size()) / 2;
        animate().setInterpolator(interpolator)
                .setDuration(mRotationTime)
                .rotation((360 * 15) + wheelItemCenter)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (mOnLuckyWheelReachTheTarget != null) {
                            mOnLuckyWheelReachTheTarget.onReachTarget();
                        }
                        clearAnimation();
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .start();
    }

    /**
     * Function to rotate wheel to target WITHOUT animation
     *
     * @param target       target number
     */
    public void rotateWheelToTargetInstantly(int target) {
        float wheelItemCenter = 270 - getAngleOfIndexTarget(target) + (360 / mWheelItems.size()) / 2;
        float rotation = (360 * 15) + wheelItemCenter;
        setRotation(rotation);
    }

    /**
     * Function to rotate to zero angle
     *
     * @param target       target to reach
     * @param interpolator custom interpolator for rotation animation
     */
    public void resetRotationLocationToZeroAngle(final int target, final Interpolator interpolator) {
        animate().setDuration(0)
                .rotation(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                rotateWheelToTarget(target, interpolator);
                clearAnimation();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * Function to rotate to zero angle WITHOUT animation
     *
     * @param target       target to reach
     */
    public void rotateWheelToInstantly(final int target) {
        animate().cancel();
        rotateWheelToTargetInstantly(target);
    }

    /**
     * Function to rotate to zero angle
     *
     * @param target target to reach
     */
    public void resetRotationLocationToZeroAngle(final int target) {
        animate().setDuration(0)
                .rotation(0).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                rotateWheelToTarget(target);
                clearAnimation();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawWheelBackground(canvas);
        initComponents();

        float tempAngle = 0;
        float sweepAngle = 360 / mWheelItems.size();

        textPaint.setTextAlign(mTextRotationHorizontal ? Paint.Align.CENTER : Paint.Align.LEFT);

        for (int i = 0; i < mWheelItems.size(); i++) {
            archPaint.setColor(mWheelItems.get(i).getColor());
            canvas.drawArc(range, tempAngle, sweepAngle, true, archPaint);
            int imgWidth = 0;
            if (mWheelItems.get(i).getBitmap() != null)
                imgWidth = drawImage(canvas, tempAngle, mWheelItems.get(i).getBitmap());
            if (mWheelItems.get(i).getText() != null && mWheelItems.get(i).getText().length() > 0)
                drawText(imgWidth, canvas, tempAngle, mWheelItems.get(i).getText(), mWheelItems.get(i).getTextColor(), mWheelItems.get(i).getTextSize());
            tempAngle += sweepAngle;
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = Math.min(getMeasuredWidth(), getMeasuredHeight());
        padding = getPaddingLeft() == 0 ? DEFAULT_PADDING : getPaddingLeft();
        radius = width - padding * 2;
        center = width / 2;
        setMeasuredDimension(width, width);

    }
}
