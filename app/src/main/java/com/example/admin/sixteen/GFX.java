package com.example.admin.sixteen;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by admin on 06-01-2016.
 */
public class GFX extends Activity {

    private MyBringBack ourView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ourView = new MyBringBack(this);
        setContentView(ourView);
    }
}
