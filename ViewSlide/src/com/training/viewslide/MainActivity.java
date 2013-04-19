package com.training.viewslide;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnTouchListener{

	private FragmentTransaction transaction;
	private ExtraFragment extraFragment;
	private float initialTouchPosition;
	private Animation animation;
	private static final Interpolator sInterpolator = new Interpolator() {
		public float getInterpolation(float t) {
			t -= 1.0f;
			return t * t * t * t * t + 1.0f;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getActionBar().hide();
		extraFragment = new ExtraFragment();
		findViewById(R.id.mainLayout).setOnTouchListener(this);
		transaction = getFragmentManager().beginTransaction();
		transaction.add(R.id.mainLayout, extraFragment, "test");
		transaction.addToBackStack(null);
		transaction.commit();
		getFragmentManager().executePendingTransactions();
		animation = new ScaleAnimation(0, 500,0, 0);
		animation.setDuration(1000);
		animation.setInterpolator(sInterpolator);
		animation.setFillAfter(true);		
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			initialTouchPosition = event.getRawX();
		}
		if(event.getAction() == MotionEvent.ACTION_MOVE){
			if(initialTouchPosition > 20){
				LayoutAnimationController controller = new LayoutAnimationController(animation);
				((RelativeLayout)getFragmentManager().findFragmentByTag("test").getView()).setLayoutAnimation(controller);
				((RelativeLayout)getFragmentManager().findFragmentByTag("test").getView()).startLayoutAnimation();
			}else{
				RelativeLayout relativeLayout = ((RelativeLayout) getFragmentManager().findFragmentByTag("test").getView());
				android.view.ViewGroup.LayoutParams layoutParams = relativeLayout.getLayoutParams();
				layoutParams.width = Math.round(v.getLeft()+ event.getRawX());
				getFragmentManager().findFragmentByTag("test").getView().requestLayout();
				v.invalidate();
				
			}
			
		}
		return true;
	}
	
}
