package com.yofly.animationdemo.viewanimation.alpha;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.yofly.animationdemo.R;

/**
 * @author: Chens
 * @description: 平移动画——使用ObjectAnimator
 * @date 2019/10/8 10:00
 */
public class AlphaDemo2Layout extends RelativeLayout {
    View view;
    Button animateBt;

    public AlphaDemo2Layout(Context context) {
        super(context);
    }

    public AlphaDemo2Layout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AlphaDemo2Layout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        view = findViewById(R.id.animatorView);
        animateBt = (Button) findViewById(R.id.animateBt);

        animateBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlphaAnimation animation = new AlphaAnimation(0f, 1f);
                animation.setDuration(2000);
                animation.setFillAfter(true);
                view.startAnimation(animation);
            }
        });
    }
}
