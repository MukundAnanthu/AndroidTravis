package com.example.admin.sixteen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by admin on 04-01-2016.
 */
public class Data extends Activity implements View.OnClickListener {

    private EditText question;
    private RadioGroup radioGroup;
    private TextView responseText;
    private Button startActivityButton;
    private Button startActivityForResultButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get);

        initialize();

    }

    private void initialize() {
        question = (EditText) findViewById(R.id.etSend);
        radioGroup = (RadioGroup) findViewById(R.id.rgAnswers);
        responseText = (TextView)findViewById(R.id.tvText);
        startActivityButton = (Button) findViewById(R.id.bSA);
        startActivityForResultButton = (Button) findViewById(R.id.bSAFR);
        startActivityButton.setOnClickListener(this);
        startActivityForResultButton.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK ) {
           // String response = data.getExtras().getString("answer");
           // responseText.setText(response);
            Bundle bas = data.getExtras();
            String s = bas.getString("answer");
            responseText.setText(s);
        }
        else {
            System.out.println("Woah Error baba");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSA:
                String bread = question.getText().toString();
                Bundle basket = new Bundle();
                basket.putString("key",bread);
                Intent a = new Intent(Data.this,OpenedClass.class);
                a.putExtras(basket);
                startActivity(a);
                break;
            case R.id.bSAFR:

                Intent sendIntent = new Intent(Data.this, OpenedClass.class);
                startActivityForResult(sendIntent, 0);
                break;
        }
    }
}
