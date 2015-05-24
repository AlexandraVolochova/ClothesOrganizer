package com.example.lenovo.clothesorganizer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


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
        final ImageButton button = (ImageButton) findViewById(R.id.buttonRefresh);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                refreshTemperature();
            }
        });

        refreshTemperature(); // при запуске программы сразу выводим температуру
    }
    // фукция загрузки температуры
    public String getTemperature(String urlsite)
    {
        String matchtemper = "";
        try
        {
            // загрузка страницы
            URL url = new URL(urlsite);
            URLConnection conn = url.openConnection();
            InputStreamReader rd = new InputStreamReader(conn.getInputStream());
            StringBuilder allpage = new StringBuilder();
            int n = 0;
            char[] buffer = new char[40000];
            while (n >= 0)
            {
                n = rd.read(buffer, 0, buffer.length);
                if (n > 0)
                {
                    allpage.append(buffer, 0, n);
                }
            }
            // работаем с регулярным выражением
            final Pattern pattern = Pattern.compile
                    ("[^-+0]+([-+0-9]+)[^<]+[^(а-яА-ЯёЁa-zA-Z0-9)]+([а-яА-ЯёЁa-zA-Z ]+)");
            Matcher matcher = pattern.matcher(allpage.toString());
            if (matcher.find())
            {
                matchtemper = matcher.group(1);
            }
            return matchtemper;
        }
        catch (Exception e)
        {

        }
        return matchtemper;
    };

    // функция обновления показаний температуры
    public void refreshTemperature()
    {
        final TextView tTemper = (TextView) findViewById(R.id.textviewTemperature);
        String bashtemp = "";
        bashtemp = getTemperature("http://be.bashkirenergo.ru/weather/ufa/");
        tTemper.setText(bashtemp.concat("°")); // отображение температуры
    };
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