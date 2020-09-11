package com.example.tony.weatherapp.interactor.interactorcity.intercatorweather;

import com.example.tony.weatherapp.presenter.presenterweather.WeatherPresenter;



public interface InteractorWeather {
    void spendRequestWeatherCities(String city, WeatherPresenter weatherPresenter);
}
