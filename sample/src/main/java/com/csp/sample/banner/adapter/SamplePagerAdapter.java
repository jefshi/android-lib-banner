package com.csp.sample.banner.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Description: ViewPager 适配器 Demo
 * Create Date: 2016-10-31
 * Modify Date: 2017-11-28
 *
 * @author csp
 * @version 1.0.1
 * @since AndroidLibrary 1.0.0
 */
@SuppressWarnings("unused")
public class SamplePagerAdapter extends BasePagerAdapter<Integer> {

	public SamplePagerAdapter(Context context, List<Integer> object) {
		super(context, object);
	}

	@Override
	protected View getView(ViewGroup container, int position) {
		Integer id = getItem(position);

		FrameLayout view = new FrameLayout(getContext());

		ImageView img = new ImageView(getContext());
		img.setImageResource(id);
		view.addView(img);

		TextView txt = new TextView(getContext());
		txt.setTextSize(60);
		txt.setText("position = " + position);
		view.addView(txt);
		return view;
	}
}
