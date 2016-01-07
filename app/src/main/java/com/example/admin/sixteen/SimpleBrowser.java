package com.example.admin.sixteen;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by admin on 07-01-2016.
 */
public class SimpleBrowser extends Activity implements View.OnClickListener {

    private EditText url;
    private WebView ourBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_browser);

        ourBrowser = (WebView)findViewById(R.id.wvBrowser);
        //ourBrowser.loadUrl();

        Button goButton = (Button)findViewById(R.id.bGo);
        Button backButton = (Button)findViewById(R.id.bBack);
        Button forwardButton = (Button)findViewById(R.id.bForward);
        Button refreshButton = (Button)findViewById(R.id.bRefresh);
        Button clrHisButton = (Button)findViewById(R.id.bClearHistory);
        url = (EditText) findViewById(R.id.etURL);
        goButton.setOnClickListener(this);
        backButton.setOnClickListener(this);
        forwardButton.setOnClickListener(this);
        refreshButton.setOnClickListener(this);
        clrHisButton.setOnClickListener(this);


        ourBrowser.setWebViewClient(new OurWebViewClient());

        //page loads with size s.t it fits the mobiles screen
        ourBrowser.getSettings().setJavaScriptEnabled(true);
        ourBrowser.getSettings().setLoadWithOverviewMode(true);
        ourBrowser.getSettings().setUseWideViewPort(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bGo:
                String theWebsite = url.getText().toString();
                ourBrowser.loadUrl(theWebsite);

                //to hide the keyboard after clicking Go button
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(url.getWindowToken(), 0);
                break;
            case R.id.bRefresh:
                ourBrowser.reload();
                break;
            case R.id.bBack:
                if(ourBrowser.canGoBack())
                    ourBrowser.goBack();
                break;
            case R.id.bClearHistory:
                ourBrowser.clearHistory();
                break;
            case R.id.bForward:
                if(ourBrowser.canGoForward())
                    ourBrowser.goForward();
                break;
        }
    }
}
