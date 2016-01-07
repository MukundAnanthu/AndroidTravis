package com.example.admin.sixteen;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by admin on 02-01-2016.
 */
public class Menu extends ListActivity {

    private String classes[] = {"MainActivity","TextPlay","Email",
            "Camera","Data","GFX","GFSSurface","SoundEffects"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //to get the full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        try {
            Class classObject = Class.forName("com.example.admin.sixteen." + classes[position]);
            Intent ourIntent = new Intent(Menu.this, classObject);
            startActivity(ourIntent);
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater blowUp = getMenuInflater();
        blowUp.inflate(R.menu.cool_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.iAboutUs:
                Intent i = new Intent("com.sixteen.ABOUT_US");
                startActivity(i);
                break;
            case R.id.iPreferences:
                Intent p = new Intent("com.sixteen.PREFS");
                startActivity(p);
                break;
            case R.id.iExit:
                finish();
                break;
        }
        return false;
    }
}
