package com.example.admin.sixteen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.View;

/**
 * Created by admin on 06-01-2016.
 */
public class MyBringBack extends View {

    private Bitmap gBall;
    private float changeY;
    private Typeface typeFace;
    public MyBringBack(Context context) {
        super(context);
        gBall = BitmapFactory.decodeResource(getResources(),R.drawable.greenball);
        changeY = 0;
        //typeFace = Typeface.createFromAsset(context.getAssets(),"g_unit.ttf");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);

        Paint textPaint = new Paint();
        textPaint.setARGB(50,254,10,50);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(50);
        //textPaint.setTypeface(typeFace);
        canvas.drawText("my bring",canvas.getWidth()/2,200, textPaint);

        canvas.drawBitmap(gBall,(canvas.getWidth()/2),changeY,null);
        if(changeY < canvas.getHeight()) {
            changeY += 10;
        }
        else{
            changeY = 0;
        }

        Rect rect = new Rect();
        rect.set(0,500,canvas.getWidth(),550);
        Paint ourPaint = new Paint();
        ourPaint.setColor(Color.BLUE);
        canvas.drawRect(rect,ourPaint);

        //makes the canvas be drawn over and over again.
        //without this, the canvas is drawn only once
        invalidate();
    }
}
