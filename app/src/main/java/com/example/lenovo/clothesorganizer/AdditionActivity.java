package com.example.lenovo.clothesorganizer;


import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import java.io.File;
import android.net.Uri;

public class AdditionActivity extends ActionBarActivity {

    String photoPath;

    //Запуск Addition Activity. При запуке в поле ImageView вставляется картинка, переданная из вызывающей активности (MainActivity)
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);
        ImageView photo = (ImageView)findViewById(R.id.imageView);
        photoPath =  getIntent().getExtras().getString("com.example.lenovo.clothesorganizer.PHOTO_PATH");
        File file = new File(photoPath);
        photo.setImageURI(Uri.fromFile(file));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_addition, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
