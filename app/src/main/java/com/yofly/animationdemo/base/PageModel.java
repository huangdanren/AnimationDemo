package com.yofly.animationdemo.base;

import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;

/**
 * @author: Chens
 * @description:
 * @date 2019/10/8 9:37
 */
public class PageModel {
    @StringRes
    public int titleRes;
    @LayoutRes
    public int animtionUtilLayoutRes;
    @LayoutRes
    public int animatorLayoutRes;

    public PageModel(@StringRes int titleRes, @LayoutRes int animtionUtilLayoutRes, @LayoutRes int animatorLayoutRes) {
        this.titleRes = titleRes;
        this.animtionUtilLayoutRes = animtionUtilLayoutRes;
        this.animatorLayoutRes = animatorLayoutRes;
    }
}
