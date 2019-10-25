package com.yofly.animationdemo.netnrbottom;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.BounceInterpolator;

import com.yofly.animationdemo.R;
import com.yofly.animationdemo.utils.DensityUtil;
import com.yofly.animationdemo.utils.Tools;

/**
 * @author: Chens
 * @description:
 * @date 2019/10/11 16:03
 */
public class BottomSwitchView extends View {

    private int unSelectedImgRes = R.drawable.ic_bottom_mine_unselected;
    private int selectedImgRes = R.drawable.ic_bottom_mine_selected;
    private String text = "我的";
    private int selectedColor = R.color.selected_blue;
    private int unSelectedColor = R.color.unselected_gray;

    private boolean isSelected = false;
    private int textSize = 10;
    private int bottomPadding = 3;
    private int imgWidth = 40;
    private int imgHeight = 40;

    private int lineMinRadius = 10;
    private int lineMaxRadius = 15;

    private Bitmap selectedImgBitmap;
    private Bitmap unSelectedImgBitmap;
    private float textHeight;
    private Paint textPaint;
    private float progress = 0;

    public BottomSwitchView(Context context) {
        this(context, null);
    }

    public BottomSwitchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        imgWidth = DensityUtil.dip2px(getContext(), imgWidth);
        imgHeight = DensityUtil.dip2px(getContext(), imgHeight);
        bottomPadding = DensityUtil.dip2px(getContext(), bottomPadding);
        textHeight = Tools.getTextHeight(DensityUtil.sp2px(getContext(), textSize));
        selectedImgBitmap = BitmapFactory.decodeResource(getResources(), selectedImgRes);
        unSelectedImgBitmap = BitmapFactory.decodeResource(getResources(), unSelectedImgRes);

        lineMinRadius = DensityUtil.dip2px(getContext(), lineMinRadius);
        lineMaxRadius = DensityUtil.dip2px(getContext(), lineMaxRadius);

        initPaint();
        initListener();
    }

    private void initListener() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!canUnselectedByself && isSelected) return;
                setViewSelected(!isSelected);
            }
        });
    }

    //设置是否可以自身取消选中
    private boolean canUnselectedByself = true;

    public void setCanUnselectedByself(boolean canUnselectedByself) {
        this.canUnselectedByself = canUnselectedByself;
    }

    public void setViewSelected(boolean isSelected) {
        this.isSelected = isSelected;

        progress = 0;
        if (valueAnimator != null && valueAnimator.isRunning()) {
            valueAnimator.cancel();
        }
        if (imgBounceAnimator != null && imgBounceAnimator.isRunning()) {
            imgBounceAnimator.cancel();
        }
        if (isSelected) {
            startAnim();
        } else {
            invalidate();
        }
    }

    private ValueAnimator valueAnimator = null;

    private void startAnim() {
        valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress = ((float) animation.getAnimatedValue());

                invalidate();
                if (progress == 1) {
                    //开始缩放动画
                    startBounceImgAnim();
                }
            }
        });
        valueAnimator.setDuration(200);
        valueAnimator.start();
    }

    private ValueAnimator imgBounceAnimator = null;
    private void startBounceImgAnim() {
        imgBounceAnimator = ValueAnimator.ofFloat(0, 1);
        imgBounceAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                progress = value + 1;

                invalidate();
            }
        });
        imgBounceAnimator.setInterpolator(new BounceInterpolator());
        imgBounceAnimator.setDuration(600);
        imgBounceAnimator.start();
    }

    private void initPaint() {
        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        //文字的中心坐标
        float textCenterX = width / 2f;
        float textCenterY = height - textHeight / 2 - bottomPadding;

        float imgCenterX = width / 2f;
        float imgCenterY = height / 2f - (textHeight + bottomPadding) / 2;
        if (!isSelected) {
            Rect imgRect = new Rect(0, 0, unSelectedImgBitmap.getWidth(), unSelectedImgBitmap.getHeight());
            //未选中状态
            Rect rect = new Rect((int) (imgCenterX - imgWidth / 2f), (int) (imgCenterY - imgHeight / 2f),
                    (int) (imgCenterX + imgWidth / 2f), (int) (imgCenterY + imgHeight / 2f));
            canvas.drawBitmap(unSelectedImgBitmap, imgRect, rect, textPaint);

            textPaint.setColor(getResources().getColor(unSelectedColor));
            textPaint.setTextSize(DensityUtil.sp2px(getContext(), textSize));
            float deltaTextY = Math.abs(textPaint.ascent() + textPaint.descent()) / 2;
            canvas.drawText(text, textCenterX, textCenterY + deltaTextY, textPaint);
        } else {
            onDrawSelected(canvas, textCenterX, textCenterY, imgCenterX, imgCenterY);
        }
    }

    private void onDrawSelected(Canvas canvas, float textCenterX, float textCenterY, float imgCenterX, float imgCenterY) {
        //选中状态（前十帧逐渐变长，然后消失）
        Path path = new Path();
        if (progress >= 0 && progress < 1) {
            float radius = lineMinRadius + (lineMaxRadius - lineMinRadius) * progress;
            //圆
            if (progress >= 0.5) {
                float circleRadius = lineMinRadius + (lineMaxRadius - lineMinRadius) * progress * progress;
                textPaint.setAlpha((int)(progress * 200 * progress));

                canvas.drawCircle(imgCenterX, imgCenterY, circleRadius, textPaint);
            }
            textPaint.setAlpha(255);
            if (progress < 0.9) {
                //射线
                for (int i = 0; i < 5; i++) {
                    double cosAngle = Math.cos(i * 2 / 5f * Math.PI);
                    double sinAngle = Math.sin(i * 2 / 5f * Math.PI);

                    path.moveTo(imgCenterX + (float) (lineMinRadius * sinAngle), imgCenterY + (float) (lineMinRadius * cosAngle));
                    path.lineTo(imgCenterX + (float) (radius * sinAngle), imgCenterY + (float) (radius * cosAngle));
                }
                textPaint.setColor(getResources().getColor(selectedColor));
                canvas.drawPath(path, textPaint);
            }
        } else {
            drawSelectedBitmap(canvas, imgCenterX, imgCenterY);
        }

        float mTextSize = DensityUtil.sp2px(getContext(), textSize) * (progress > 1 ? (progress - 1) : 1);
        textPaint.setTextSize(mTextSize);
        float deltaTextY = Math.abs(textPaint.ascent() + textPaint.descent()) / 2;
        canvas.drawText(text, textCenterX, textCenterY + deltaTextY, textPaint);
    }

    private void drawSelectedBitmap(Canvas canvas, float imgCenterX, float imgCenterY) {
        if (progress < 1.2 && progress >= 1) {
            //射线
            textPaint.setStyle(Paint.Style.FILL);
            textPaint.setAlpha((int) (255 - 255 * ((1.2 - progress) / 0.2)));
            for (int i = 0; i < 5; i++) {
                double cosAngle = Math.cos(i * 2 / 5f * Math.PI);
                double sinAngle = Math.sin(i * 2 / 5f * Math.PI);

                canvas.drawCircle(imgCenterX + (float) (lineMaxRadius * progress * sinAngle),
                        imgCenterY + (float) (lineMaxRadius * progress * cosAngle), 1f, textPaint);
            }
            textPaint.setColor(getResources().getColor(selectedColor));
            textPaint.setStyle(Paint.Style.STROKE);
            textPaint.setAlpha(255);
        }
        Rect imgRect = new Rect(0, 0, selectedImgBitmap.getWidth(), selectedImgBitmap.getHeight());
        int selectImgWidth = (int) (imgWidth * (progress - 1));
        int selectImgHeight = (int) (imgHeight * (progress - 1));
        textPaint.setAlpha((int) ((progress - 1) * 255));
        //未选中状态
        Rect rect = new Rect((int) (imgCenterX - selectImgWidth / 2f), (int) (imgCenterY - selectImgHeight / 2f),
                (int) (imgCenterX + selectImgWidth / 2f), (int) (imgCenterY + selectImgHeight / 2f));
        canvas.drawBitmap(selectedImgBitmap, imgRect, rect, textPaint);
    }
}
