package com.example.admin.sixteen;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

/**
 * Created by admin on 07-01-2016.
 */
public class Tabs extends Activity implements View.OnClickListener {

    private TabHost th;
    private TextView showResults;
    private long startTime,stopTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabs);

        Button newTab = (Button) findViewById(R.id.bAddTab);
        Button start = (Button) findViewById(R.id.bStart);
        Button stop = (Button) findViewById(R.id.bStop);
        showResults = (TextView) findViewById(R.id.tvDisplayResults);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        newTab.setOnClickListener(this);

        th = (TabHost) findViewById(R.id.tabHost);
        th.setup();
        TabHost.TabSpec specs = th.newTabSpec("tag1");
        specs.setContent(R.id.tab1);
        specs.setIndicator("Stopwatch");
        th.addTab(specs);

        specs = th.newTabSpec("tag2");
        specs.setContent(R.id.tab2);
        specs.setIndicator("Tab 2");
        th.addTab(specs);

        specs = th.newTabSpec("tag3");
        specs.setContent(R.id.tab3);
        specs.setIndicator("Add a Tab");
        th.addTab(specs);
        startTime = (long) 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.bAddTab:
                TabHost.TabSpec ourSpec = th.newTabSpec("tag1");
                ourSpec.setContent(new TabHost.TabContentFactory() {
                    @Override
                    public View createTabContent(String tag) {
                        TextView text = new TextView(Tabs.this);
                        text.setText("You have created a new tab");
                        return text;
                    }
                });
                ourSpec.setIndicator("New");
                th.addTab(ourSpec);
                break;
            case R.id.bStart:
                startTime = System.currentTimeMillis();
                break;
            case R.id.bStop:

                stopTime = System.currentTimeMillis();

                if ( startTime != 0 ) {
                    long timeElapsed = stopTime - startTime;
                    int milliSeconds = (int) timeElapsed;
                    int secs = (int) milliSeconds / 1000;
                    int mins = (int) secs / 60;
                    milliSeconds %= 100;
                    showResults.setText(String.format("%d:%02d:%02d",mins,secs,milliSeconds));
                }
                break;
        }
    }
}
