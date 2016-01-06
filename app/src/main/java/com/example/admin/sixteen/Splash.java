package com.example.admin.sixteen;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * Created by admin on 02-01-2016.
 */


public class Splash extends Activity {

    private MediaPlayer startingSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        startingSong = MediaPlayer.create(Splash.this,R.raw.skyfall);
        SharedPreferences getPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean playPreference = getPrefs.getBoolean("checkbox",true);
        if (playPreference == true)
            startingSong.start();

        Thread thread = new Thread(){
            public void run() {
                try {
                    sleep(3000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    Intent openMainActivity = new Intent("com.sixteen.MENU");
                    startActivity(openMainActivity);
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        startingSong.release();
        finish();
    }
}
