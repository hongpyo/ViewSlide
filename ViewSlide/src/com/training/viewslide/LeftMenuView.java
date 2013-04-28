package com.training.viewslide;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;

public class LeftMenuView extends ViewGroup{

	private static final boolean FINGER_MOVEMENT_RIGHT = true;
	private static final boolean FINGER_MOVEMENT_LEFT = false;
	private static final int MAX_SETTLE_DURATION = 600; // ms
	private Scroller scroller;
	private float initialPosition;
	private float finalPosition;
	private TextView headingTextView;
	private RelativeLayout relativeLayout;
	private LayoutParams source;
	private View mainViewShadow;
	private static final Interpolator sInterpolator = new Interpolator() {
		public float getInterpolation(float t) {
			t -= 1.0f;
			return t * t * t * t * t + 1.0f;
		}
	};
	private VelocityTracker velocityTracker;
	private ViewConfiguration viewConfiguration;

	public LeftMenuView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
	}
	
	public LeftMenuView(Context context, LayoutParams source, View mainViewShadow) {
		super(context);
		this.source = source;
		this.mainViewShadow = mainViewShadow;
		scroller = new Scroller(context, sInterpolator);
		viewConfiguration = ViewConfiguration.get(context);
		relativeLayout = new RelativeLayout(context);
		relativeLayout.setBackgroundColor(Color.parseColor("#ffffff"));
		headingTextView = new TextView(context);
		headingTextView.setText("feedly");
		headingTextView.setTextColor(Color.parseColor("#000000"));
		relativeLayout.addView(headingTextView);
		addView(relativeLayout);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(velocityTracker == null){
			velocityTracker = VelocityTracker.obtain();
		}
		velocityTracker.addMovement(event);
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			initialPosition = finalPosition = event.getX();
			break;
		case MotionEvent.ACTION_MOVE:
			float dx = initialPosition - event.getX();
			if (menuSlideAllowed(dx)) {
				final float x = event.getX();
				final float deltaX = finalPosition - x;
				finalPosition = x;
				float oldScrollX = getScrollX();
				float scrollX = oldScrollX + deltaX;
				// Don't lose the rounded component
				finalPosition += scrollX - (int) scrollX;
				scrollTo((int) scrollX, getScrollY());
			}
			break;
		case MotionEvent.ACTION_UP:
			if(beingDragged(event)){
				velocityTracker.computeCurrentVelocity(1000, viewConfiguration.getScaledMaximumFlingVelocity());
				smoothScrollTo(event.getX(), velocityTracker.getXVelocity());
				velocityTracker.recycle();
			}
			break;
		}
		return true;
	}
	
	private boolean beingDragged(MotionEvent event) {
		float dx = initialPosition - event.getX();
		return menuSlideAllowed(dx);
	}

	private boolean menuSlideAllowed(float dx) {
		if(dx > 0){
			return FINGER_MOVEMENT_RIGHT;
		}
		return FINGER_MOVEMENT_LEFT;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		relativeLayout.layout(l, t, r, b);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = getDefaultSize(0, widthMeasureSpec);
		int height = getDefaultSize(0, heightMeasureSpec);
		setMeasuredDimension(source.width, height);
		final int contentWidth = getChildMeasureSpec(widthMeasureSpec, 0, source.width);
		final int contentHeight = getChildMeasureSpec(heightMeasureSpec, 0, height);
		relativeLayout.measure(contentWidth, contentHeight);
	}	
	
	@Override
	public void computeScroll() {
		if (!scroller.isFinished() && scroller.computeScrollOffset()) {
			int oldX = getScrollX();
			int oldY = getScrollY();
			int x = scroller.getCurrX();
			int y = scroller.getCurrY();

			if (oldX != x || oldY != y) {
				scrollTo(x, y);
			}
			invalidate();
			return;
		}
		if (getScrollX() == source.width) {
			completeScroll();
		}
	}
	
	private void completeScroll() {
			setVisibility(View.GONE);
			scroller.forceFinished(true);
			mainViewShadow.setVisibility(View.GONE);
	}
	
	void smoothScrollTo(float velocity, float xPosition) {
		int sx = getScrollX();
		int sy = getScrollY();
		int dx = source.width - sx;
		if(dx > 400){
			dx = -getScrollX();
		}
		int duration = computeDuration(velocity, dx);
		System.out.println("Duration is --->>>"+duration);
		if(duration < 250){
			duration = MAX_SETTLE_DURATION; 
		}
		scroller.startScroll(sx, sy, dx, 0, 500);
		invalidate();
	}	
	
	private int computeDuration(float velocity, int distance){
		velocity = Math.abs(velocity);
		return 4 * Math.round(100 * Math.abs(distance / velocity));
	}

}
