package com.yofly.animationdemo.svg;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.yofly.animationdemo.R;

public class SVGAnimationDemoActivity extends AppCompatActivity {

    ImageView iv;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0) {
                startAnim();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svganimation_demo);

        iv = (ImageView) findViewById(R.id.iv);

        startAnim();
    }

    private void startAnim() {
        // 启动动画（类似于AnimationDrawable的使用 ）
        Drawable drawable = iv.getDrawable();
        if (drawable instanceof Animatable) {
            Animatable animatable = (Animatable) drawable;
            animatable.start();
        }
        mHandler.sendEmptyMessageDelayed(0, 1200);
    }
}
