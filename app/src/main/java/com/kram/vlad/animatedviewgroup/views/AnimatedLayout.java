package com.kram.vlad.animatedviewgroup.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.kram.vlad.animatedviewgroup.activitys.MainActivity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by vlad on 27.09.17.
 * Custom Animated RelativeLayout
 */

public class AnimatedLayout extends RelativeLayout {

    public static final String TAG = AnimatedLayout.class.getSimpleName();

    public static final int DURATION_LOWER_RANGE = 4;
    public static final int DURATION_UPPER_RANGE = 9;
    public static final int ROTATION_LOWER_RANGE = 0;
    public static final int ROTATION_UPPER_RANGE = 360;
    public static final int FINISHED_ROTATION = 250;
    public static final int DURATION = 1000;

    public AnimatedLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimatedLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AnimatedLayout(Context context) {
        super(context);
    }

    public void fallViews() {

        MainActivity.animated = true;
        for (int i = 0; i < getChildCount(); i++) {

            final int buf = i;
            final long duration = (new Random().nextInt(DURATION_UPPER_RANGE - DURATION_LOWER_RANGE) + DURATION_UPPER_RANGE) * 1000;
            final long rotation = (new Random().nextInt(ROTATION_UPPER_RANGE - ROTATION_LOWER_RANGE) + ROTATION_UPPER_RANGE);
            getChildAt(i).animate().rotationY(rotation).setDuration(duration - 4000)
                    .withEndAction(() ->getChildAt(buf).animate().rotationY(360).setDuration(3500))
                    .start();
            getChildAt(i).animate().translationY(getHeight() - (getChildAt(i).getTop() + getChildAt(i).getHeight())).setDuration(1000);
            getChildAt(i).animate().rotationX(FINISHED_ROTATION).setDuration(duration);
        }
    }

    public void toStartPositions(ArrayList<Float> yPositions) {

        MainActivity.animated = false;
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).animate().translationY(yPositions.get(i)).setDuration(DURATION);
            getChildAt(i).animate().rotationX(0).setDuration(DURATION);
            getChildAt(i).animate().rotationY(0).setDuration(DURATION).withEndAction(() -> MainActivity.animated = false)
                    .start();
        }
    }
}