package com.yofly.animationdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.yofly.animationdemo.property.PropertyAnimDemoActivity;
import com.yofly.animationdemo.svg.SVGAnimationDemoActivity;
import com.yofly.animationdemo.utils.DensityUtil;
import com.yofly.animationdemo.viewanimation.ViewAnimationDemoActivity;

public class MainActivity extends AppCompatActivity {
    private LinearLayout llContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        llContainer = ((LinearLayout) findViewById(R.id.ll_container));

        addItem("查看View视图动画", ViewAnimationDemoActivity.class);
        addItem("查看属性动画", PropertyAnimDemoActivity.class);
        addItem("查看SVG动画", SVGAnimationDemoActivity.class);

    }

    private void addItem(String text, final Class<?> cls) {
        Button btn = new Button(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                DensityUtil.dip2px(this, 40f));
        lp.leftMargin = DensityUtil.dip2px(this, 20f);
        lp.rightMargin = DensityUtil.dip2px(this, 20f);
        btn.setLayoutParams(lp);
        btn.setText(text);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, cls));
            }
        });

        llContainer.addView(btn);
    }
}
