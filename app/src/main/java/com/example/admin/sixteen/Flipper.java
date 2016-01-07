package com.example.admin.sixteen;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

/**
 * Created by admin on 07-01-2016.
 */
public class Flipper extends Activity implements View.OnClickListener{

    private ViewFlipper flippy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flipper);
        flippy = (ViewFlipper) findViewById(R.id.viewFlipper);
        flippy.setOnClickListener(this);
        //flippy.
    }

    @Override
    public void onClick(View v) {
        flippy.showNext();
    }
}
