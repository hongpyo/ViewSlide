package com.training.viewslide;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnTouchListener{

	private FragmentTransaction transaction;
	private ExtraFragment extraFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		extraFragment = new ExtraFragment();
		findViewById(R.id.mainLayout).setOnTouchListener(this);
		transaction = getFragmentManager().beginTransaction();
		transaction.add(R.id.mainLayout, extraFragment, "test");
		transaction.addToBackStack(null);
		transaction.commit();
		getFragmentManager().executePendingTransactions();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setDisplayShowCustomEnabled(true);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflator.inflate(R.layout.action_bar, null);
		getActionBar().setCustomView(v);
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
		return true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_MOVE){
			RelativeLayout relativeLayout = ((RelativeLayout) getFragmentManager().findFragmentByTag("test").getView());
			android.view.ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
			layoutParams.width = Math.round(v.getLeft()+ event.getRawX());
			getFragmentManager().findFragmentByTag("test").getView().requestLayout();
			v.invalidate();
			
		}
		return true;
	}
	
}
