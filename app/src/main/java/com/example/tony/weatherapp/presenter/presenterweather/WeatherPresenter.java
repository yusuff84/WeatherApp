package com.example.tony.weatherapp.presenter.presenterweather;

import com.example.tony.weatherapp.model.modelweather.WeatherCities;



public interface WeatherPresenter {
    void onSuccessCities(WeatherCities weatherCities);

    void onValidateWeather(String city);


}
