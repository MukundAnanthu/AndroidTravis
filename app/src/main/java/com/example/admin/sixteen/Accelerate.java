package com.example.admin.sixteen;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by admin on 09-01-2016.
 */
public class Accelerate extends Activity implements SensorEventListener {

    private float x, y, sensorX, sensorY;
    private Bitmap ball;
    private SensorManager sm;
    private MySurfaceBringBack ourSurface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ourSurface = new MySurfaceBringBack(this);
        ourSurface.resume();
        setContentView(ourSurface);
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if ( sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0 ) {
            Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(this,s,SensorManager.SENSOR_DELAY_NORMAL);
        }
        ball = BitmapFactory.decodeResource(getResources(),R.drawable.greenball);
        x = y = sensorX = sensorY = 0;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sensorX = event.values[0];
        sensorY = event.values[1];
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

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
                float centerX = canvas.getWidth() / 2;
                float centerY = canvas.getHeight() / 2;
                canvas.drawBitmap(ball,centerX+sensorX*20, centerY+sensorY*20,null);
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }
}
