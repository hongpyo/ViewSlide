package com.training.viewslide;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends Activity{

	private LeftMenuView leftMenuView;
	private RelativeLayout mainPageLayout;
	private View mainViewShadow;
	private Animation startAnimation;
	private Animation exitAnimation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().hide();
		setContentView(R.layout.activity_main);
		mainPageLayout = (RelativeLayout)findViewById(R.id.mainLayout);
		mainViewShadow = mainPageLayout.findViewById(R.id.shadow);
		@SuppressWarnings("deprecation")
		int actualScreenWidth = getWindowManager().getDefaultDisplay().getWidth();
		float width = (float) (actualScreenWidth * 0.80);
		LayoutParams layoutParams = new LayoutParams((int)width, LayoutParams.MATCH_PARENT);
		leftMenuView = new LeftMenuView(getApplicationContext(), layoutParams, mainViewShadow);
		leftMenuView.setVisibility(View.INVISIBLE);
		mainPageLayout.addView(leftMenuView);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_enter);
			exitAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_exit);
		}
		if(event.getAction() == MotionEvent.ACTION_DOWN && leftMenuView.getVisibility() == View.VISIBLE){
			exitAnimation.setAnimationListener(new ExitAnimationListener());
			leftMenuView.startAnimation(exitAnimation);
			leftMenuView.setVisibility(View.GONE);
		}
		if(event.getAction() == MotionEvent.ACTION_MOVE && event.getRawX() < 20 && !startAnimation.hasStarted()){
			leftMenuView.scrollTo(0, 0);
			mainViewShadow.setVisibility(View.VISIBLE);
			leftMenuView.startAnimation(startAnimation);
			leftMenuView.setVisibility(View.VISIBLE);
		}
		return true;
	}
	
	@Override
	public void onBackPressed() {
		mainPageLayout.findViewById(R.id.shadow).setVisibility(View.GONE);
		leftMenuView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_exit));
		leftMenuView.setVisibility(View.GONE);
	}
	
	class ExitAnimationListener implements AnimationListener{

		@Override
		public void onAnimationEnd(Animation arg0) {
			mainViewShadow.setVisibility(View.GONE);
		}

		@Override
		public void onAnimationRepeat(Animation arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onAnimationStart(Animation arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
}
