package com.example.lenovo.clothesorganizer;


import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WardrobeCategoryActivity extends ActionBarActivity {
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardrobe_category);
        type = getIntent().getExtras().getString("com.example.lenovo.clothesorganizer.THING_TYPE");
        switch (type){
            case "Up":
                setTitle("Up");
                break;
            case "Down":
                setTitle("Down");
                break;
            case "Footwear":
                setTitle("Footwear");
                break;
            case "Accessories":
                setTitle("Accessories");
                break;
            default:
                break;
        }
        final ListView lisView1 = (ListView)findViewById(R.id.listViewCategory);
        //создаём помошника для работы с базой
        DBHelper dbHelper = new DBHelper(this);
        //подключаемся к базе
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //курсор
        Cursor cursor = null;
        String[] sType = null;
        sType = new String[] {type};
        cursor = db.query("myThing", null, null, null, null, null, null);
        if(cursor.moveToFirst()) {
            lisView1.setAdapter(new MyWardrobeAdapter(this, cursor));
        }
        else {
            TextView textView = (TextView) findViewById(R.id.empty);
            textView.setText("I don't want to work");
        }
        cursor.close();
        dbHelper.close();

    }
    

    //Методы для меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}