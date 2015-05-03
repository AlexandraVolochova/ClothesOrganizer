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
    String photoPath;

    //Запуск MainActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDirectory();
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

    //Метод для запуска CreationActivity
    public void openCreationActivity(View view) {
        Intent i = new Intent(MainActivity.this, CreationActivity.class);
        startActivity(i);
    }

    //Метод для запуска WardrobeActivity
    public void openWardrobeActivity(View view) {
        Intent i = new Intent(MainActivity.this, WardrobeActivity.class);
        startActivity(i);
    }

    //Вызов камеры с передачей параметра
    public void onClickAdd(View view) {
        photoPath = directory.getPath() + "/" +"photo_" + System.currentTimeMillis() + ".jpg"; // используем системное время, как основную часть имени файла
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(photoPath);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        startActivityForResult(intent, REQUEST_CODE_PHOTO);
    }

    // Метод для создания папки, где хранятся фотографии
    private void createDirectory() {
        directory = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "MyWardrobe");
        if (!directory.exists())
            directory.mkdirs();
    }

    //То, что делает приложение, после того, как камера вернула ему управление
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_CODE_PHOTO) {
            if (resultCode == RESULT_OK) {
             Intent i = new Intent(MainActivity.this, AdditionActivity.class);
             i.putExtra("com.example.lenovo.clothesorganizer.PHOTO_PATH", photoPath);
             startActivity(i);
            }


        }
    }
}