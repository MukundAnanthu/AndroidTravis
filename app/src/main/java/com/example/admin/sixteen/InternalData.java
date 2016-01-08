package com.example.admin.sixteen;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by admin on 08-01-2016.
 */
public class InternalData extends Activity implements View.OnClickListener {

    private EditText sharedData;
    private TextView dataResults;
    private FileOutputStream fos;
    private String FILE_NAME = "InternalString";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_prefs);
        setUpVariables();

    }

    private void setUpVariables() {
        Button save = (Button) findViewById(R.id.bSave);
        Button load = (Button) findViewById(R.id.bLoad);
        save.setOnClickListener(this);
        load.setOnClickListener(this);
        sharedData = (EditText) findViewById(R.id.etSharedPrefs);
        dataResults = (TextView) findViewById(R.id.tvLoadSharedPrefs);
        try {
            fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSave:
                try {
                    String data = sharedData.getText().toString();
                    fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                    fos.write(data.getBytes());
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                /*
                alternative way to write to file
                File file = new File(FILE_NAME);
                fos = new FileOutputStream(file);
                //write data to file
                fos.close();
                * */
                break;
            case R.id.bLoad:
                new LoadSomeStuff().execute(FILE_NAME);
                break;
        }
        
    }

    private class LoadSomeStuff extends AsyncTask<String,Integer,String> {

        protected void onPreExecute(String f) {
            //set up stuff
            f = "whatever";
        }

        protected void onProgressUpdate(Integer...progress) {

        }

        protected void onPostExecute(String result){
            dataResults.setText(result);
        }

        @Override
        protected String doInBackground(String... params) {
            String collected = null;
            FileInputStream fis = null;
            try {
                fis = openFileInput(FILE_NAME);
                byte[] dataArray = new byte[fis.available()];
                while(fis.read(dataArray) != -1 ) {
                    collected = new String(dataArray);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                assert fis != null;
                try {
                    fis.close();
                    return collected;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
    }
}

