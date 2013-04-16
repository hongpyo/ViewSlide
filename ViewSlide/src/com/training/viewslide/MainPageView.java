package com.training.viewslide;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MainPageView extends View {

	public MainPageView(Context context) {
		super(context);
	}
	
	public MainPageView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
	}

	
	@Override
	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(0x29888888);
		canvas.drawPaint(paint);
	}
}
