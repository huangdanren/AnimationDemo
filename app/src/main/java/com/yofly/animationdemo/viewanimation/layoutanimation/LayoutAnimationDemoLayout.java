package com.yofly.animationdemo.viewanimation.layoutanimation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.yofly.animationdemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Chens
 * @description:
 * @date 2019/10/21 9:22
 */
public class LayoutAnimationDemoLayout extends RelativeLayout {

    private ListView listView;

    public LayoutAnimationDemoLayout(Context context) {
        super(context);
    }

    public LayoutAnimationDemoLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        listView = ((ListView) findViewById(R.id.listview));

        final List<String> list = new ArrayList<>();

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        findViewById(R.id.animateBt).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                //代码中设置
                Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.right_to_left_enter);
                LayoutAnimationController controller = new LayoutAnimationController(animation);
                controller.setDelay(0.5f);
                controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
                listView.setLayoutAnimation(controller);
                for (int i = 0; i < 30; i++) {
                    list.add("item" + i);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}
