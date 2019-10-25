package com.yofly.animationdemo.viewanimation.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yofly.animationdemo.R;

/**
 * @author: Chens
 * @description:
 * @date 2019/10/20 10:41
 */
public class CustromAnimationLayout extends RelativeLayout {
    View view;
    Button animateBt;

    public CustromAnimationLayout(Context context) {
        super(context);
    }

    public CustromAnimationLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustromAnimationLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        view = findViewById(R.id.animatorView);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "view is clicked !!!", Toast.LENGTH_SHORT).show();
            }
        });
        animateBt = (Button) findViewById(R.id.animateBt);

        animateBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Rotate3dAnimation rotate3dAnimation = new Rotate3dAnimation(0f, 360f, 100f, 100f, 50f, true);
                rotate3dAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                rotate3dAnimation.setDuration(2000);
                view.startAnimation(rotate3dAnimation);
            }
        });
    }

}
