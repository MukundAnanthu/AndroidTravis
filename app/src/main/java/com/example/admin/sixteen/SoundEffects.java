package com.example.admin.sixteen;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

/**
 * Created by admin on 07-01-2016.
 */
public class SoundEffects extends Activity implements View.OnClickListener{

    private SoundPool sp;
    private int explosion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = new View(this);
        v.setOnClickListener(this);
        setContentView(v);
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        explosion = sp.load(this,R.raw.skyfall,1);
    }

    @Override
    public void onClick(View v) {
        if(explosion != 0 )
            sp.play(explosion,1,1,0,0,1);
    }
}
