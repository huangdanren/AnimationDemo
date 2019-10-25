package com.yofly.animationdemo.property;


import android.view.animation.Interpolator;

import com.yofly.animationdemo.utils.MyLog;

/**
 * @author: Chens
 * @description:
 * @date 2019/10/24 13:36
 */
public class SpeedUp2Intepolator implements Interpolator {

    @Override
    public float getInterpolation(float v) {
        MyLog.e("input: " + v);
        float speed;
        if (v < 0.5f) {
            speed = v * v;
        } else {
            speed = v;
        }
        return 0;
    }
}
