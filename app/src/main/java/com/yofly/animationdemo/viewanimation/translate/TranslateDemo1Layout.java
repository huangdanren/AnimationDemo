package com.yofly.animationdemo.viewanimation.translate;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yofly.animationdemo.R;
import com.yofly.animationdemo.utils.MyLog;

/**
 * @author: Chens
 * @description: 平移动画——使用anim xml
 * @date 2019/10/8 10:00
 */
public class TranslateDemo1Layout extends RelativeLayout {
    View view;
    Button animateBt;

    public TranslateDemo1Layout(Context context) {
        super(context);
    }

    public TranslateDemo1Layout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TranslateDemo1Layout(Context context, AttributeSet attrs, int defStyleAttr) {
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
                //方案一：使用anim xml进行配置
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.viewanim_translate);
                view.startAnimation(animation);
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        MyLog.e("onAnimationStart: ");
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        MyLog.e("onAnimationEnd: ");
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        MyLog.e("onAnimationRepeat: ");
                    }
                });

                //方案二：使用TranslationAnimation
                //view实际位置没发生变化
//                TranslateAnimation animation = new TranslateAnimation(Animation.ABSOLUTE, 0f,
//                        Animation.ABSOLUTE, 400, Animation.RELATIVE_TO_SELF,
//                        0f, Animation.RELATIVE_TO_SELF, 0f);
//                animation.setDuration(500);
//                animation.setFillAfter(true);
//                view.startAnimation(animation);
            }
        });
    }
}
