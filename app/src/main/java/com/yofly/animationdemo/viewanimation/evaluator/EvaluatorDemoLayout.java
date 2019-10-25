package com.yofly.animationdemo.viewanimation.evaluator;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yofly.animationdemo.R;
import com.yofly.animationdemo.utils.MyLog;
import com.yofly.animationdemo.viewanimation.custom.Rotate3dAnimation;

/**
 * @author: Chens
 * @description:
 * @date 2019/10/23 20:05
 */
public class EvaluatorDemoLayout extends RelativeLayout {

    private View view;
    private Button animateBt;

    public EvaluatorDemoLayout(Context context) {
        super(context);
    }

    public EvaluatorDemoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
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
                //evaluator
                startEvaluator();
            }
        });
    }

    private void startEvaluator() {
        ValueAnimator animator = new ValueAnimator();
        animator.setDuration(3000);
        animator.setObjectValues(new PointF(0, 0));
        final PointF pointF = new PointF();
        animator.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                MyLog.e("fraction: " + fraction + "  startValue；" + startValue + "  endValue" + endValue);
                //fraction是运动中的匀速变化的值
                //根据重力计算实际的运动y=vt=0.5*g*t*t
                //g越大效果越明显
                pointF.x = 200 * (fraction * 5);
                pointF.y = 0.5f * 100f * (fraction * 5) * (fraction * 5);
                return pointF;
            }
        });
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF p = (PointF) animation.getAnimatedValue();
                view.setX(p.x);
                view.setY(p.y);
            }
        });
        animator.start();
    }
}
