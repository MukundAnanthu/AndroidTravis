package com.example.admin.sixteen;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.os.Environment.DIRECTORY_MUSIC;

/**
 * Created by admin on 08-01-2016.
 */
public class ExternalStorage extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private TextView canRead,canWrite;
    private String state;
    private boolean canW,canR;
    private Spinner spinner;
    private File file;
    private Button confirm, save;
    private EditText saveFile;
    private String[] paths = {"Music","Pictures","Download"};
    private File path = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.external_storage);
        canRead = (TextView) findViewById(R.id.tvCanRead);
        canWrite = (TextView) findViewById(R.id.tvCanWrite);
        state = Environment.getExternalStorageState();
        confirm = (Button) findViewById(R.id.bconfSave);
        save = (Button) findViewById(R.id.bSave);
        confirm.setOnClickListener(this);
        save.setOnClickListener(this);
        saveFile = (EditText) findViewById(R.id.etSave);
        checkState();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExternalStorage.this,
                android.R.layout.simple_spinner_item, paths);

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    private void checkState() {
        if ( state.equals(Environment.MEDIA_MOUNTED) ) {
            canRead.setText("true");
            canWrite.setText("true");
            canW = canR = true;

        } else if ( state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            canRead.setText("true");
            canWrite.setText("false");
            canR = true;
            canW = false;
        } else {
            canRead.setText("false");
            canWrite.setText("false");
            canR = canW = false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int po = spinner.getSelectedItemPosition();
        switch (po) {
            case 0:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                break;
            case 1:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                break;
            case 2:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bSave:
                String f = saveFile.getText().toString();
                file = new File(path,f);

                checkState();

                if ( canW == canR == true) {
                        path.mkdirs();
                        try {
                        InputStream is = getResources().openRawResource(R.drawable.greenball);
                        OutputStream os = new FileOutputStream(file);
                        byte[] data = new byte[is.available()];
                        is.read(data);
                        os.write(data);
                        is.close();
                        os.close();

                        Toast t = Toast.makeText(ExternalStorage.this,"File has been saved", Toast.LENGTH_LONG);
                        t.show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                break;
            case R.id.bconfSave:
                break;
        }
    }
}
