package com.training.viewslide;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainPageView extends RelativeLayout {

	public MainPageView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
	}
	
	public MainPageView(Context context) {
		super(context);
		setBackgroundColor(Color.parseColor("#58D3F7"));
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		setLayoutParams(layoutParams);
		TextView headingTextView = new TextView(context);
		headingTextView.setText("feedly");
		headingTextView.setTextColor(Color.parseColor("#ffffff"));
		addView(headingTextView);
	}

}
