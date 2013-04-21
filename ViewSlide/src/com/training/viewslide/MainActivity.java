package com.training.viewslide;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends Activity implements OnTouchListener{

	private FragmentTransaction transaction;
	private ExtraFragment extraFragment;
	private float initialTouchPosition;
	private Animation animation;
	private float mLastMotionX;
	private static final Interpolator sInterpolator = new Interpolator() {
		public float getInterpolation(float t) {
			t -= 1.0f;
			return t * t * t * t * t + 1.0f;
		}
	};
	private LeftMenuView leftMenuView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().hide();
		RelativeLayout mainPageLayout = (RelativeLayout)findViewById(R.id.mainLayout);
		LayoutParams layoutParams = new LayoutParams(500, LayoutParams.MATCH_PARENT);
		leftMenuView = new LeftMenuView(getApplicationContext(), layoutParams);
		leftMenuView.setLayoutParams(layoutParams);
		leftMenuView.setVisibility(View.INVISIBLE);
		mainPageLayout.addView(leftMenuView);
		findViewById(R.id.mainLayout).setOnTouchListener(this);
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			leftMenuView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_enter));
			leftMenuView.setVisibility(View.VISIBLE);
		}
		return true;
	}
	
}
