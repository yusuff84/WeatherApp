package com.example.tony.weatherapp.view.citiesview;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tony.weatherapp.R;
import com.example.tony.weatherapp.room.AppDatabase;
import com.example.tony.weatherapp.room.Image;
import com.example.tony.weatherapp.view.weatherview.WeatherActivity;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    final String THITHER_CITY = "thither";
    final String BACK_CITY = "back";
    @BindView(R.id.date_thither)
    TextView thitherDateTv;
    @BindView(R.id.date_back)
    TextView backDateTv;
    @BindView(R.id.thither_city_ll)
    LinearLayout firstCityLl;
    @BindView(R.id.thither_date_ll)
    LinearLayout thitherDateLl;
    @BindView(R.id.thither_city)
    TextView thitherCityTv;
    @BindView(R.id.back_city_name)
    TextView backCityTv;
    @BindView(R.id.back_date_button)
    Button backDateBtn;
    @BindView(R.id.back_date_ll)
    LinearLayout backDateLl;
    @BindView(R.id.logoBaseDate)
    ImageView logoBaseDate;

    DatePickerDialog datePickerDialogThither;
    DatePickerDialog datePickerDialogBack;
    Calendar calendar;
    private String thitherCityName;
    private String backCityName;
    private SharedPreferences sharedPreferences;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.custom_toolbar_main);

        calendar = Calendar.getInstance();

        Intent intent = getIntent();


        if (intent != null) {
            String whereCity = intent.getStringExtra("whereCity");
            if (whereCity != null) {
                if (whereCity.equals("thither")) {
                    thitherCityName = intent.getStringExtra("nameCity");
                    saveNamesCity();
                    thitherCityTv.setText(thitherCityName);
                } else {
                    backCityName = intent.getStringExtra("nameCity");
                    saveNamesCity();
                    backCityTv.setText(backCityName);
                }
            }
        } else {
            thitherCityTv.setText("Калининград");
            backCityTv.setText("Москва");
        }
        loadNamesCity();

        //база данных сохраняет данные в файле с именем populus-database
         db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "populus-database").build();


        //logoBaseDate = (ImageView) findViewById(R.id.logoBaseDate)

        new AgentAsyncTask(this, db).execute();


    }



    public void showLogo(final List<Image> images){
                int random  = 0 + (int)(Math.random() * ((2 - 0) + 1));
                Picasso.get().load(images.get(random).getUrl()).into(logoBaseDate);
    }



    @OnClick(R.id.thither_city_ll)
    public void transitionToSelection(View view) {

    }

    @OnClick(R.id.thither_date_ll)
    public void showDatePickerThither(View view) {
        datePickerDialogThither = new DatePickerDialog(
                MainActivity.this, MainActivity.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialogThither.show();
        datePickerDialogBack = null;
    }

    @OnClick(R.id.back_date_ll)
    public void showDatePickerBack(View view) {
        datePickerDialogBack = new DatePickerDialog(
                MainActivity.this, MainActivity.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialogBack.show();
        datePickerDialogThither = null;
    }


    @OnClick(R.id.back_invisible_btn)
    public void backInvisible(View view) {
        backDateLl.setVisibility(View.GONE);
        backDateBtn.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.back_city_ll)
    public void visibleBackDate(View view) {

    }

    @OnClick(R.id.back_date_button)
    public void visibleBackDate(Button button) {
        backDateLl.setVisibility(View.VISIBLE);
        button.setVisibility(View.GONE);
    }

    @OnClick(R.id.next_weather_button)
    public void transitionWeather(Button button) {
        String cityThither = thitherCityTv.getText().toString();
        String cityBack = backCityTv.getText().toString();

        Intent intent = new Intent(this, WeatherActivity.class);
        intent.putExtra("cityThither", cityThither);
        intent.putExtra("cityBack", cityBack);
        startActivity(intent);
    }

    @OnClick(R.id.trade_city_btn)
    public void tradeCities(View view) {
        String cityBack = backCityTv.getText().toString();
        String cityThither = thitherCityTv.getText().toString();

        backCityTv.setText(cityThither);
        thitherCityTv.setText(cityBack);

    }

    public void saveNamesCity() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        if (thitherCityName != null) {
            ed.putString(THITHER_CITY, thitherCityName);
        } else if (backCityName != null) {
            ed.putString(BACK_CITY, backCityName);
        }
        ed.commit();
    }

    public void loadNamesCity() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        thitherCityName = sharedPreferences.getString(THITHER_CITY, "Ekaterinburg");
        backCityName = sharedPreferences.getString(BACK_CITY, "Moscow");
        if (thitherCityName != null) {
            thitherCityTv.setText(thitherCityName);
        } else if (backCityName != null) {
            backCityTv.setText(backCityName);
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat("dd MMM,E");
        Date date = new Date(year, month, dayOfMonth);
        String customDate = simpledateformat.format(date);

        if (datePickerDialogThither == null) {
            backDateTv.setText(customDate);
        } else if (datePickerDialogBack == null) {
            thitherDateTv.setText(customDate);

        }
    }


    private static class AgentAsyncTask extends AsyncTask<Void, Void, List<Image>> {

        //Prevent leak
        private WeakReference<Activity> weakActivity;
        AppDatabase db;


        public AgentAsyncTask(Activity activity,AppDatabase db) {
            weakActivity = new WeakReference<>(activity);
            this.db = db;
        }

        @Override
        protected List<Image> doInBackground(Void... params) {


            List<Image> images = db.getImageDao().getAllImage();

            if(images.size()==0){
                db.getImageDao().insertAll(new Image("https://i.ibb.co/VmZJjXV/1.png"));
                db.getImageDao().insertAll(new Image("https://i.ibb.co/4K52DRk/2.png"));
                db.getImageDao().insertAll(new Image("https://i.ibb.co/PxF6kMB/3.png"));
                db.getImageDao().insertAll(new Image("https://i.ibb.co/jvHgqJs/4.png"));
                db.getImageDao().insertAll(new Image("https://i.ibb.co/gtqs2SX/5.png"));
                db.getImageDao().insertAll(new Image("https://i.ibb.co/tHX7jf1/6.png"));
                db.getImageDao().insertAll(new Image("https://i.ibb.co/MNsXPLr/7.png"));
                db.getImageDao().insertAll(new Image("https://i.ibb.co/YXNrQsF/8.png"));
                db.getImageDao().insertAll(new Image("https://i.ibb.co/YRcBfVb/9.png"));

            }

            return db.getImageDao().getAllImage();
        }

        @Override
        protected void onPostExecute(List<Image> images) {
            Activity activity = weakActivity.get();
            if(activity == null) {
                return;
            }else{
                ((MainActivity) activity).showLogo(images);
            }
        }
    }


}