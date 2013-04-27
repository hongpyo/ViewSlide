package com.training.viewslide;

import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;

public class SlideMenuView extends RelativeLayout {

	public SlideMenuView(Context context) {
		super(context);
		setBackgroundColor(Color.parseColor("#ffffff"));
		LayoutParams layoutParams = new LayoutParams(0, LayoutParams.MATCH_PARENT);
		setLayoutParams(layoutParams);
	}

}
