package com.yofly.animationdemo.viewanimation;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

import com.yofly.animationdemo.R;

public class PageFragment extends Fragment {
    @LayoutRes
    int animtionUtilLayoutRes;
    @LayoutRes
    int animatorLayoutRes;

    public static PageFragment newInstance(@LayoutRes int animtionUtilLayoutRes, @LayoutRes int animatorLayoutRes) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt("animtionUtilLayoutRes", animtionUtilLayoutRes);
        args.putInt("animatorLayoutRes", animatorLayoutRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);

        ViewStub animtionUtilStub = (ViewStub) view.findViewById(R.id.animtionUtilStub);
        animtionUtilStub.setLayoutResource(animtionUtilLayoutRes);
        animtionUtilStub.inflate();

        ViewStub animatorStub = (ViewStub) view.findViewById(R.id.animatorStub);
        if (animatorLayoutRes == -1) {
            ((ViewGroup) animatorStub.getParent()).setVisibility(View.GONE);
        } else {
            animatorStub.setLayoutResource(animatorLayoutRes);
            animatorStub.inflate();
        }

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            animtionUtilLayoutRes = args.getInt("animtionUtilLayoutRes");
            animatorLayoutRes = args.getInt("animatorLayoutRes");
        }
    }
}
