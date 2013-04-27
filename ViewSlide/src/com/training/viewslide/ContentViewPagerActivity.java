package com.training.viewslide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class ContentViewPagerActivity extends FragmentActivity{
	
	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		viewPager = new ContentViewPager(this);
		setContentView(viewPager);
		viewPager.setAdapter(new ContentPageAdapter(getSupportFragmentManager()));
	}
	
	private class ContentPageAdapter extends FragmentPagerAdapter{

		public ContentPageAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
}
