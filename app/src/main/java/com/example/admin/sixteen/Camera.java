package com.example.admin.sixteen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by admin on 02-01-2016.
 */
public class Camera extends Activity implements View.OnClickListener {

    private ImageButton imageButton;
    private Button takePicButton;
    private ImageView imageView;
    private Intent takePicIntent;
    private final static int cameraResults = 0;
    private Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.splash);
        setContentView(R.layout.photo);
        inintialize();
        InputStream is = getResources().openRawResource(R.drawable.ic_launcher);
        bmp = BitmapFactory.decodeStream(is);
    }

    private void inintialize() {

        imageButton = (ImageButton)findViewById(R.id.ibTakePic);
        takePicButton = (Button)findViewById(R.id.bSetWallpaper);
        imageView = (ImageView)findViewById(R.id.ivReturnedImage);
        takePicButton.setOnClickListener(this);
        imageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.bSetWallpaper:
                try {
                    getApplicationContext().setWallpaper(bmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.ibTakePic:
                takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(takePicIntent,cameraResults);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK ) {
            Bundle extras = data.getExtras();
            bmp = (Bitmap) extras.get("data");
            imageView.setImageBitmap(bmp);
        }
    }
}
