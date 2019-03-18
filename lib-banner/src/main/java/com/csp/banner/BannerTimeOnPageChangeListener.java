package com.csp.banner;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;


/**
 * 自动轮播用 OnPageChangeListener
 * Created by csp on 2019/3/14.
 *
 * @version 1.0.0
 */
public class BannerTimeOnPageChangeListener extends BannerOnPageChangeListener {

    private long mIntervalTime = 3000;
    private boolean carousel = true;

    public void setIntervalTime(long intervalTime) {
        if (intervalTime > 0)
            mIntervalTime = intervalTime;
        else
            throw new IllegalArgumentException("间隔时间大于 0");
    }

    public void setCarousel(boolean carousel) {
        this.carousel = carousel;
    }

    public BannerTimeOnPageChangeListener(@NonNull final ViewPager viewPager, ViewPager.OnPageChangeListener listener) {
        super(viewPager, listener);
    }

    public void startCarousel() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!carousel)
                    return;

                mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
                handler.postDelayed(this, mIntervalTime);
            }
        }, mIntervalTime);
    }

    public void stopCarousel() {
        carousel = false;
    }
}
