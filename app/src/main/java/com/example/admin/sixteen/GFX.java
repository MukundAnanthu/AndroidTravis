package com.example.admin.sixteen;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;

/**
 * Created by admin on 06-01-2016.
 */
public class GFX extends Activity {

    private MyBringBack ourView;
    private PowerManager.WakeLock wL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wL = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK,
                "whatever");

        super.onCreate(savedInstanceState);
        wL.acquire();
        ourView = new MyBringBack(this);
        setContentView(ourView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        wL.release();
    }
}
