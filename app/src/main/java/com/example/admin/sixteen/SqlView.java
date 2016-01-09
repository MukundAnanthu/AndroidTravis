package com.example.admin.sixteen;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by admin on 09-01-2016.
 */
public class SqlView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sql_view);
        TextView tv = (TextView) findViewById(R.id.tvSQLInfo);
        WatchOrNot info = new WatchOrNot(this);
        try {
            info.open();
            String data = info.getData();
            info.close();
            tv.setText(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
