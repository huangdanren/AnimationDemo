package com.yofly.animationdemo.utils;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * @author hedewen
 * <p>
 * createtime:2013-5-23下午4:00:08
 */
public class Tools {

    public static int gettextwidth(Context context, String str, int fontsize) {
        TextView tv = new TextView(context);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontsize);
        Paint paint = tv.getPaint();
        return (int) paint.measureText(str);
    }

    public static float getTextHeight(float fontSize) {
        Paint paint = new Paint();
        paint.setTextSize(fontSize);
        FontMetrics fm = paint.getFontMetrics();
        return (float) Math.ceil(fm.descent - fm.ascent);
    }
}
