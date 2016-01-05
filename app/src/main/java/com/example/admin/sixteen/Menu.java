package com.example.admin.sixteen;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by admin on 02-01-2016.
 */
public class Menu extends ListActivity {

    private String classes[] = {"MainActivity","TextPlay","Email","Camera","Data"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(Menu.this,android.R.layout.simple_list_item_1,classes));
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
}
