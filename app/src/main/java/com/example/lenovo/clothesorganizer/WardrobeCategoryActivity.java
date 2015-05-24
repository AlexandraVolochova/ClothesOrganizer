package com.example.lenovo.clothesorganizer;


import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WardrobeCategoryActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardrobe_category);
        switch (getIntent().getExtras().getString("com.example.lenovo.clothesorganizer.THING_TYPE")){
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