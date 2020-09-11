package com.example.tony.weatherapp.interactor.interactorcity.intercatorweather;

import android.util.Log;

import com.example.tony.weatherapp.Constant;
import com.example.tony.weatherapp.CustomApplication;
import com.example.tony.weatherapp.model.modelweather.WeatherCities;
import com.example.tony.weatherapp.network.MyRetrofit;
import com.example.tony.weatherapp.presenter.presenterweather.WeatherPresenter;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class InteractorWeatherImpl implements InteractorWeather {
    @Inject
    MyRetrofit retrofit;


    @Override
    public void spendRequestWeatherCities(String city, final WeatherPresenter weatherPresenter) {

        CustomApplication.getRetrofitComponent().injectInteractorWeather(this);

        city = city.concat(",ru");


        retrofit.getApi("http://api.openweathermap.org/data/2.5/").getWeatherCities(city, Constant.API_KEY_WEATHER).enqueue(new Callback<WeatherCities>() {
            @Override
            public void onResponse(Call<WeatherCities> call, Response<WeatherCities> response) {
                if (response.body() != null) {
                    response.body().getList();
                    weatherPresenter.onSuccessCities(response.body());
                }
            }

            @Override
            public void onFailure(Call<WeatherCities> call, Throwable t) {
                Log.d("NotResponse", t.getMessage());
                call.request();

            }
        });

    }
}



