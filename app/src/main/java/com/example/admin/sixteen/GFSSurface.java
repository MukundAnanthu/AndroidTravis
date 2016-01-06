package com.example.admin.sixteen;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by admin on 06-01-2016.
 */

public class GFSSurface extends Activity implements View.OnTouchListener {

    private MySurfaceBringBack ourSurfaceView;
    private float x,y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ourSurfaceView = new MySurfaceBringBack(this);
        ourSurfaceView.setOnTouchListener(this);
        setContentView(ourSurfaceView);
        x = y = 0;
    }

    @Override
    protected void onResume() {
        super.onResume();
        ourSurfaceView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ourSurfaceView.pause();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        x = event.getX();
        y = event.getY();


        return true;
    }

    public class MySurfaceBringBack extends SurfaceView implements Runnable {

        private SurfaceHolder ourHolder;
        private Thread ourThread;
        private boolean isRunning = false;

        public MySurfaceBringBack(Context context) {
            super(context);
            ourHolder = getHolder();
        }


        public void resume() {
            isRunning = true;
            ourThread = new Thread(this);
            ourThread.start();

        }

        public void pause() {
            isRunning = false;

            while(true) {

                try {
                    ourThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
            ourThread = null;
        }

        @Override
        public void run() {

            while(isRunning) {

                if(!ourHolder.getSurface().isValid())
                    continue;

                Canvas canvas = ourHolder.lockCanvas();
                canvas.drawColor(Color.rgb(2, 2, 250));
                if ( x!= 0 && y != 0) {
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.greenball);
                    canvas.drawBitmap(bmp,x-bmp.getWidth()/2,y-bmp.getHeight()/2,null);
                }
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
