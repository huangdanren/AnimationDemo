package com.yofly.animationdemo.property;

import android.animation.ArgbEvaluator;
import android.animation.TypeEvaluator;

public class MyTypeEvaluator implements TypeEvaluator<PropertyBean> {
    ArgbEvaluator mArgbEvaluator = new ArgbEvaluator();

    @Override
    public PropertyBean evaluate(float fraction, PropertyBean startPropertyBean, PropertyBean endPropertyBean) {
        int currentColor = (int) mArgbEvaluator.evaluate(fraction, startPropertyBean.getBackgroundColor(), endPropertyBean.getBackgroundColor());
        float currentRotationX = startPropertyBean.getRotationX() + (endPropertyBean.getRotationX() - startPropertyBean.getRotationX()) * fraction;
        float currentSize = startPropertyBean.getSize() + (endPropertyBean.getSize() - startPropertyBean.getSize()) * fraction;
        return new PropertyBean((int) currentColor, currentRotationX, currentSize);
    }
}
