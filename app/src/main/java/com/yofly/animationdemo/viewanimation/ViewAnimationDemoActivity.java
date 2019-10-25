package com.yofly.animationdemo.viewanimation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.yofly.animationdemo.R;
import com.yofly.animationdemo.base.PageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Chens
 * @description: View视图动画
 * @date 2019/10/8 9:32
 */
public class ViewAnimationDemoActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager pager;
    List<PageModel> pageModels = new ArrayList<>();

    {
        pageModels.add(new PageModel(R.string.view_translate, R.layout.layout_translate_demo1,
                R.layout.layout_translate_demo2));
        pageModels.add(new PageModel(R.string.view_animation, R.layout.layout_custom_animation,
                -1));
        pageModels.add(new PageModel(R.string.view_layout_animation, R.layout.layout_listview_animation,
                -1));
        pageModels.add(new PageModel(R.string.view_evaluator, R.layout.layout_evaluator_animation,
                -1));
        pageModels.add(new PageModel(R.string.view_transparent, R.layout.layout_translate_demo1,
                R.layout.layout_translate_demo1));
        pageModels.add(new PageModel(R.string.view_scale, R.layout.layout_translate_demo1,
                R.layout.layout_translate_demo1));
        pageModels.add(new PageModel(R.string.view_rotate, R.layout.layout_translate_demo1,
                R.layout.layout_translate_demo1));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_animation);

        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                PageModel pageModel = pageModels.get(position);
                return PageFragment.newInstance(pageModel.animtionUtilLayoutRes, pageModel.animatorLayoutRes);
            }

            @Override
            public int getCount() {
                return pageModels.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return getString(pageModels.get(position).titleRes);
            }
        });

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(pager);
    }
}
