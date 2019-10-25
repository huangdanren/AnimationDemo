package com.yofly.animationdemo.viewanimation.evaluator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author: Chens
 * @description:
 * @date 2019/10/23 20:07
 */
public class CircleView extends View {


    private Paint paint;

    public CircleView(Context context) {
        super(context);

        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        float radius = width / 2f;
        canvas.drawCircle(width / 2f, height / 2f, radius, paint);
    }
}
