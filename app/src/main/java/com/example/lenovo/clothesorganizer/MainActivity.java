package com.example.lenovo.clothesorganizer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

import java.io.File;


public class MainActivity extends ActionBarActivity {

    File directory;
    public final int REQUEST_CODE_PHOTO = 1;
    String photoPath;

    private TextView cityText;
    private TextView condDescr;
    private TextView temp;
    private TextView press;
    private TextView windSpeed;
    private TextView windDeg;
    private TextView hum;
    private ImageView imgView;


    //Запуск MainActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createDirectory();
        //String city = "Moscow,Ru";

        cityText = (TextView) findViewById(R.id.cityText);
        condDescr = (TextView) findViewById(R.id.condDescr);
        temp = (TextView) findViewById(R.id.temp);
        hum = (TextView) findViewById(R.id.hum);
        press = (TextView) findViewById(R.id.press);
        windSpeed = (TextView) findViewById(R.id.windSpeed);
        imgView = (ImageView) findViewById(R.id.condIcon);

       // JSONWeatherTask task = new JSONWeatherTask();
       // task.execute(new String[]{city});
    }


    //Методы для меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }




        public void onClicweather (View view) {
            Weather weather = new Weather();
            String data = ( (new WeatherHttpClient()).getWeatherData());

            try {
                weather = JSONWeatherParser.getWeather(data);

                // Let's retrieve the icon
                weather.iconData = ( (new WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));

            } catch (JSONException e) {
                e.printStackTrace();
            }
           // return weather;


       // protected void onPostExecute(Weather weather) {
           // super.onPostExecute(weather);

            if (weather.iconData != null && weather.iconData.length > 0) {
                Bitmap img = BitmapFactory.decodeByteArray(weather.iconData, 0, weather.iconData.length);
                imgView.setImageBitmap(img);
            }

            cityText.setText(weather.location.getCity() + "," + weather.location.getCountry());
            condDescr.setText("");
            temp.setText("" + Math.round((weather.temperature.getTemp() - 273.15)) + " C");
            hum.setText("" + weather.currentCondition.getHumidity() + "%");
            press.setText("" + weather.currentCondition.getPressure() + " hPa");
            windSpeed.setText("");
            windDeg.setText("");

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