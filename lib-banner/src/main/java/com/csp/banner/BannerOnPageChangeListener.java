package com.csp.banner;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;


/**
 * 轮播用 OnPageChangeListener
 * Created by csp on 2019/3/14.
 *
 * @version 1.0.0
 */
public class BannerOnPageChangeListener implements ViewPager.OnPageChangeListener {

    protected ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mListener;
    private int mPosition;

    public BannerOnPageChangeListener(@NonNull ViewPager viewPager, ViewPager.OnPageChangeListener listener) {
        mViewPager = viewPager;
        mListener = listener;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // TODO 从头位置滑动到尾位置时，scroll 没有回调了
        position = fixPosition(position);
        if (mListener != null && position >= 0)
            mListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        mPosition = position;
        position = fixPosition(position);
        if (mListener != null && position >= 0)
            mListener.onPageSelected(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            PagerAdapter adapter = mViewPager.getAdapter();
            if (!(adapter instanceof BannerPagerAdapter))
                throw new RuntimeException("请使用 BannerPagerAdapter，防止位置监听不正确");

            final int count = adapter.getCount();
            if (mPosition == 0) {
                mViewPager.setCurrentItem(count - 2, false);
            } else if (mPosition == count - 1) {
                mViewPager.setCurrentItem(1, false);
            }
        }

        // 使用 setCurrentItem(n, false) 不会引起 state 变化，所以不检查
        if (mListener != null)
            mListener.onPageScrollStateChanged(state);
    }

    /**
     * @return 当前位置是否是头尾（是头尾时，需要跳转页面）
     */
    private boolean isHeadOrTail(int position) {
        PagerAdapter adapter = mViewPager.getAdapter();
        if (!(adapter instanceof BannerPagerAdapter))
            throw new RuntimeException("请使用 BannerPagerAdapter，防止位置监听不正确");

        return position == 0 || position == adapter.getCount() - 1;
    }

    /**
     * @return 获取原 Adapter 的位置
     */
    private int fixPosition(int position) {
        return isHeadOrTail(position) ? -1 : (position - 1);
    }
}
