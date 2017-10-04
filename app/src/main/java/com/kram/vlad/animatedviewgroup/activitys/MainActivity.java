package com.kram.vlad.animatedviewgroup.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.kram.vlad.animatedviewgroup.R;
import com.kram.vlad.animatedviewgroup.views.AnimatedLayout;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener{

    public static final String TAG = MainActivity.class.getSimpleName();

    public static boolean animated = false;
    private ArrayList<Float> viewsPositions;

    @BindView(R.id.animated_layout) AnimatedLayout mAnimatedLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        animated = false;
        userInterfaceInit();
    }



    private ArrayList<Float> getViewsPositions(AnimatedLayout animatedLayout) {
        ArrayList<Float> yPositions = new ArrayList<>();
        for (int i = 0; i < animatedLayout.getChildCount(); i++) {
            yPositions.add(i, animatedLayout.getChildAt(i).getY());//getY());
        }
        return yPositions;
    }

    private void userInterfaceInit() {
        viewsPositions = getViewsPositions(mAnimatedLayout);
        mAnimatedLayout.setOnClickListener(this);
        mAnimatedLayout.setOnLongClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(!animated) mAnimatedLayout.fallViews();
    }

    @Override
    public boolean onLongClick(View view) {
        if(animated) mAnimatedLayout.toStartPositions(viewsPositions);
        return true;
    }
}
