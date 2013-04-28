package com.training.viewslide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ContentViewPagerAdapter extends FragmentStatePagerAdapter {

	public ContentViewPagerAdapter(FragmentManager fragmentManager) {
		super(fragmentManager);
	}

	@Override
	public Fragment getItem(int arg0) {
		return new ContentViewFragment();
	}

	@Override
	public int getCount() {
		return 2;
	}


}
