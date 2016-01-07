package com.example.admin.sixteen;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by admin on 07-01-2016.
 */
public class SharedPrefs extends Activity implements View.OnClickListener {

    private EditText sharedData;
    private TextView dataResults;
    private SharedPreferences sharedPreferences;
    private static String fn = "mysharedprefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_prefs);
        setUpVariables();
        sharedPreferences = getSharedPreferences(fn,0);
    }

    private void setUpVariables() {
        Button save = (Button) findViewById(R.id.bSave);
        Button load = (Button) findViewById(R.id.bLoad);
        save.setOnClickListener(this);
        load.setOnClickListener(this);
        sharedData = (EditText) findViewById(R.id.etSharedPrefs);
        dataResults = (TextView) findViewById(R.id.tvLoadSharedPrefs);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSave:
                String stringData = sharedData.getText().toString();
                SharedPreferences.Editor editor =  sharedPreferences.edit();
                editor.putString("sharedString",stringData);
                editor.commit();
                break;
            case R.id.bLoad:
                sharedPreferences = getSharedPreferences(fn,0);
                String loadString = sharedPreferences.getString("sharedString","Couldn't load");
                dataResults.setText(loadString);
                break;
        }
    }
}
