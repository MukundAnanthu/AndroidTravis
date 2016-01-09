package com.example.admin.sixteen;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by admin on 09-01-2016.
 */
public class SqliteExample extends Activity implements View.OnClickListener {

    private Button update;
    private Button view;
    private EditText name;
    private EditText rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqliteexample);
        createUIBridge();
        setEventListenersForButtons();

    }

    private void setEventListenersForButtons() {
        update.setOnClickListener(this);
        view.setOnClickListener(this);
    }

    private void createUIBridge() {

        update = (Button) findViewById(R.id.bUpdate);
        view = (Button) findViewById(R.id.bView);
        name = (EditText) findViewById(R.id.etNameEntered);
        rating = (EditText) findViewById(R.id.etRating);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bView:
                Intent i = new Intent("com.sixteen.SQL_VIEW");
                startActivity(i);
                break;
            case R.id.bUpdate:
                boolean didItWork = true;

                try {
                    String enteredName = name.getText().toString();
                    String enteredRating = rating.getText().toString();

                    WatchOrNot entry = new WatchOrNot(SqliteExample.this);
                    entry.open();
                    entry.createEntry(enteredName, enteredRating);
                    entry.close();
                }catch (Exception e) {
                    didItWork = false;
                }finally {
                    if(didItWork) {
                        Dialog d = new Dialog(this);
                        d.setTitle("Heck YEah");
                        TextView tv = new TextView(this);
                        tv.setText("Success");
                        d.setContentView(tv);
                        d.show();
                    }
                }
                break;
        }

    }
}
