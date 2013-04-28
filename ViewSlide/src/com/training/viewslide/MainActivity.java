package com.training.viewslide;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class MainActivity extends FragmentActivity{

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
		ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
		viewPager.setAdapter(new ContentViewPagerAdapter(getSupportFragmentManager()));
		viewPager.setPageTransformer(false, new ContentViewPageTransformer());
		viewPager.setCurrentItem(1);
		mainPageLayout.addView(leftMenuView);
		
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float initialPosition = 0f;
		switch (event.getAction()){
			case MotionEvent.ACTION_DOWN:
				initialPosition = event.getX();
				initialiseAnimation();
				if(leftMenuView.getVisibility() == View.VISIBLE){
					exitLeftMenuView();
				}
				break;
			case MotionEvent.ACTION_MOVE:
				if(moveMainMenu(event.getX(), initialPosition)){
					System.out.println("move card right");
				}
				if(event.getRawX() < 20 && !startAnimation.hasStarted()){
					enterLeftViewMenu();
				}
				break;
			}
		return true;
	}

	private boolean moveMainMenu(float xPosition, float initialPosition) {
		float xDiff = xPosition - initialPosition;
		if(xDiff > 0){
			return true;
		}
		return false;
	}

	private void initialiseAnimation() {
		startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_enter);
		exitAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_exit);
	}

	private void exitLeftMenuView() {
		exitAnimation.setAnimationListener(new ExitAnimationListener());
		leftMenuView.startAnimation(exitAnimation);
		leftMenuView.setVisibility(View.GONE);
	}

	private void enterLeftViewMenu() {
		leftMenuView.scrollTo(0, 0);
		mainViewShadow.setVisibility(View.VISIBLE);
		leftMenuView.startAnimation(startAnimation);
		leftMenuView.setVisibility(View.VISIBLE);
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
