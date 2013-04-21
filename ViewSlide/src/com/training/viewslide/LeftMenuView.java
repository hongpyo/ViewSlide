package com.training.viewslide;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LeftMenuView extends RelativeLayout {

	public LeftMenuView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
	}
	
	public LeftMenuView(Context context, LayoutParams source) {
		super(context);
		setBackgroundColor(Color.parseColor("#ffffff"));
		LayoutParams layoutParams = new LayoutParams(source);
		setLayoutParams(layoutParams);
		TextView headingTextView = new TextView(context);
		headingTextView.setText("feedly");
		headingTextView.setTextColor(Color.parseColor("#000000"));
		addView(headingTextView);
		setVisibility(View.INVISIBLE);
	}
	
}
