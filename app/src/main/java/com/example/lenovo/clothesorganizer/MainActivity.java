package com.example.lenovo.clothesorganizer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.provider.MediaStore;
import android.os.Environment;
import java.io.File;
import android.net.Uri;

public class MainActivity extends ActionBarActivity {

    File directory;
    public final int REQUEST_CODE_PHOTO = 1;
    String photoName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDirectory();
    }


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

    public void openCreationActivity(View view) {
        Intent i = new Intent(MainActivity.this, CreationActivity.class);
        startActivity(i);
    }

    public void openWardrobeActivity(View view) {
        Intent i = new Intent(MainActivity.this, WardrobeActivity.class);
        startActivity(i);
    }

    public void onClickAdd(View view) {
        photoName = "photo_" + System.currentTimeMillis() + ".jpg"; // используем системное время, как основную часть имени файла
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Создаем путь к файлу. Для этого берем путь из directory и имя фотографии
        // Далее все это записывается в поле интента extra, конвертируясь в Uri
        File file = new File(directory.getPath() + "/" + photoName);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, REQUEST_CODE_PHOTO);
    }

    private void createDirectory() {
        directory = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "MyWardrobe");
        if (!directory.exists())
            directory.mkdirs();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_CODE_PHOTO) {
            if (resultCode == RESULT_OK) {
             Intent i = new Intent(MainActivity.this, AdditionActivity.class);
             i.putExtra("com.example.lenovo.clothesorganizer.PHOTO_NAME", photoName);
             startActivity(i);
            }


        }
    }
}