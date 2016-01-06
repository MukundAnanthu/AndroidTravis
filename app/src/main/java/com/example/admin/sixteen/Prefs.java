package com.example.admin.sixteen;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by admin on 06-01-2016.
 */
public class Prefs extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }
}
