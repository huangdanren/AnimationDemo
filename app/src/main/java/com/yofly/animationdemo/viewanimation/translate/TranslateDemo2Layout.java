package com.yofly.animationdemo.viewanimation.translate;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yofly.animationdemo.R;
import com.yofly.animationdemo.utils.MyLog;

/**
 * @author: Chens
 * @description: 平移动画——使用ObjectAnimator
 * @date 2019/10/8 10:00
 */
public class TranslateDemo2Layout extends RelativeLayout {
    View view;
    Button animateBt;

    public TranslateDemo2Layout(Context context) {
        super(context);
    }

    public TranslateDemo2Layout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TranslateDemo2Layout(Context context, AttributeSet attrs, int defStyleAttr) {
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

                //方案一
//                PropertyValuesHolder holder = PropertyValuesHolder.ofFloat("alpha", 0.2f, 1);
//                ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, holder)
//                        .setDuration(500);
//                objectAnimator
//                        .start();
                //方案二
                //使用ViewPropertyAnimatorm,改变了属性
//                ViewPropertyAnimator animate = view.animate();
//                animate.setDuration(500)
//                        .setInterpolator(new AnticipateInterpolator())
//                        .translationX(400);

                //方案三：使用ObjectAnimator
//                ObjectAnimator translationX = ObjectAnimator.ofFloat(view, "translationX", 0f, 400f);
//                translationX.setDuration(500);
//                translationX.start();

                //方案四：使用ValueAnimator
                final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 400f);
                valueAnimator.setDuration(500);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (float) animation.getAnimatedValue();
                        MyLog.e(value + "");
                        //每次执行动画Value产生的值都不一定一样，这跟Android绘制机制有关16.6ms刷新一次
                        if (value >= 120 && value <= 152) {
                            valueAnimator.pause();
                            postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    valueAnimator.resume();
                                }
                            }, 1000);
                        }
                        view.setTranslationX(value);
                    }
                });
                valueAnimator.start();

                //方案五：PropertyValuesHolder
//                PropertyValuesHolder alphaHolder = PropertyValuesHolder.ofFloat("alpha", 0.5f, 1f);
//                PropertyValuesHolder translateHolder = PropertyValuesHolder.ofFloat("translationX", 0, 400f);
//                ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(view, alphaHolder, translateHolder)
//                        .setDuration(500);
//                objectAnimator.start();

            }
        });
    }
}
