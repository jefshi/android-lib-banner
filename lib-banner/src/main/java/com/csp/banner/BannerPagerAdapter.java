package com.csp.banner;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;


/**
 * 轮播用 PagerAdapter
 * Created by csp on 2019/3/14.
 *
 * @version 1.0.0
 */
public class BannerPagerAdapter extends PagerAdapter {

    private PagerAdapter mAdapter;

    public BannerPagerAdapter(PagerAdapter adapter) {
        mAdapter = adapter;
    }

    @Override
    public int getCount() {
        return mAdapter.getCount() + 2;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return mAdapter.isViewFromObject(view, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (position == 0) {
            position = mAdapter.getCount() - 1;
        } else if (position == getCount() - 1) {
            position = 0;
        } else {
            position -= 1;
        }
        return mAdapter.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        mAdapter.destroyItem(container, position, object);
    }
}
