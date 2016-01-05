package com.example.admin.sixteen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by admin on 04-01-2016.
 */
public class OpenedClass extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private Button returnButton;
    private TextView question, text;
    private RadioGroup rgSelectionList;
    //private String rcvdText;
    private String replyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        initialize();
        /*
        Bundle getBasket = getIntent().getExtras();
        rcvdText = (String) getBasket.get("key");
        question.setText(rcvdText);
        */
    }

    private void initialize() {
        returnButton = (Button) findViewById(R.id.bReturn);
        question = (TextView)findViewById(R.id.tvQuestion);
        text = (TextView) findViewById(R.id.tvText);
        rgSelectionList = (RadioGroup) findViewById(R.id.rgAnswers);
        returnButton.setOnClickListener(this);
        rgSelectionList.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent person = new Intent();
        Bundle backPack = new Bundle();
        backPack.putString("answer", (String) text.getText());
        person.putExtras(backPack);
        setResult(RESULT_OK, person);
        finish();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rbBoth:
                replyText = "Spot On!";
                break;
            case R.id.rbFun:
                replyText = "Probably Right!";
                break;
            case R.id.rbProfitable:
                replyText = "Absolutely!";
                break;
        }
        text.setText(replyText);
    }
}
