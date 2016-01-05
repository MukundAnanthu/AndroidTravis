package com.example.admin.sixteen;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * Created by admin on 02-01-2016.
 */
public class TextPlay extends Activity {

    private Button tryCommandButton;


    private TextView displayText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);

        tryCommandButton = (Button) findViewById(R.id.bTryCommand);
        final ToggleButton toggleButton = (ToggleButton) findViewById(R.id.tbPassword);
        final EditText editText = (EditText)findViewById(R.id.eCommandText);
        displayText = (TextView)findViewById(R.id.tvTextDisplayForPassword);

        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( toggleButton.isChecked() ) {
                    editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                else {
                    editText.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });

        tryCommandButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String enteredPassword = editText.getText().toString();

                if(enteredPassword.equals("left")) {
                    displayText.setGravity(Gravity.LEFT);
                }
                else if(enteredPassword.equals("right")) {
                    displayText.setGravity(Gravity.RIGHT);
                }
                else if(enteredPassword.equals("center")) {
                    displayText.setGravity(Gravity.CENTER);
                }
                else if(enteredPassword.equals("blue")) {
                    displayText.setTextColor(Color.BLUE);
                }
            }
        });
    }
}
