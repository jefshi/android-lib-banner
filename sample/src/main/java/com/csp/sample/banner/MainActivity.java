package com.csp.sample.banner;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.csp.banner.BannerOnPageChangeListener;
import com.csp.banner.BannerPagerAdapter;
import com.csp.banner.BannerTimeOnPageChangeListener;
import com.csp.sample.banner.adapter.SamplePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = this;
        final ViewPager vpgBanner = findViewById(R.id.vpg_banner);
        final ViewPager vpgBannerAuto = findViewById(R.id.vpg_banner_auto);
        final TextView txt = findViewById(R.id.txt);
        final TextView txtAuto = findViewById(R.id.txt_auto);

        // 轮播数据：10 条
        List<Integer> resIds = new ArrayList<>();
        resIds.add(R.mipmap.ic_launcher);
        resIds.add(R.mipmap.ic_launcher_round);
        resIds.add(R.mipmap.ic_launcher);
        resIds.add(R.mipmap.ic_launcher_round);
        resIds.add(R.mipmap.ic_launcher);
        resIds.add(R.mipmap.ic_launcher_round);
        resIds.add(R.mipmap.ic_launcher);
        resIds.add(R.mipmap.ic_launcher_round);
        resIds.add(R.mipmap.ic_launcher);
        resIds.add(R.mipmap.ic_launcher_round);

        // 轮播
        ViewPager.OnPageChangeListener listener = new SampleListener(txt);
        final SamplePagerAdapter adapter = new SamplePagerAdapter(context, resIds);
        vpgBanner.setAdapter(new BannerPagerAdapter(adapter));
        vpgBanner.addOnPageChangeListener(new BannerOnPageChangeListener(vpgBanner, listener));
        vpgBanner.setCurrentItem(1);

        // 自动轮播
        ViewPager.OnPageChangeListener listener02 = new SampleListener(txtAuto);
        BannerTimeOnPageChangeListener bannerListener = new BannerTimeOnPageChangeListener(vpgBannerAuto, listener02);
        bannerListener.setIntervalTime(5000); // 自动轮播间隔时间
        bannerListener.startCarousel(); // 开始自动轮播

        // final SamplePagerAdapter adapter = new SamplePagerAdapter(context, resIds);
        vpgBannerAuto.setAdapter(new BannerPagerAdapter(adapter));
        vpgBannerAuto.addOnPageChangeListener(bannerListener);
        vpgBannerAuto.setCurrentItem(1);
    }

    private static class SampleListener implements ViewPager.OnPageChangeListener {

        private TextView mTxt;
        private int mPosition;

        private SampleListener(TextView txt) {
            mTxt = txt;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            String format = String.format("position = %s, positionOffset = %s, positionOffsetPixels = %s", position, positionOffset, positionOffsetPixels);
            LogCat.e(format);
        }

        @Override
        public void onPageSelected(int position) {
            mPosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            String text = String.format("当前状态 = %s，选中第 %s 页", state, mPosition);
            mTxt.setText(text);
        }
    }
}
