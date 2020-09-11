package com.example.tony.weatherapp.presenter.presenterweather;

import com.example.tony.weatherapp.interactor.interactorcity.intercatorweather.InteractorWeather;
import com.example.tony.weatherapp.interactor.interactorcity.intercatorweather.InteractorWeatherImpl;
import com.example.tony.weatherapp.model.modelweather.WeatherCities;
import com.example.tony.weatherapp.view.weatherview.WeatherCityOneFragment;



public class WeatherPresenterImpl implements WeatherPresenter {
    InteractorWeather interactorWeather;
    WeatherCityOneFragment weatherCityOneFragment;


    public WeatherPresenterImpl(WeatherCityOneFragment weatherCityOneFragment) {
        this.interactorWeather = new InteractorWeatherImpl();
        this.weatherCityOneFragment = weatherCityOneFragment;

    }

    @Override
    public void onSuccessCities(WeatherCities weatherCities) {
        if (weatherCities != null) {
            weatherCityOneFragment.showWeather(weatherCities);
        }
    }

    @Override
    public void onValidateWeather(String city) {
        //показывает максимально известную погоду
        interactorWeather.spendRequestWeatherCities(city, this);
    }
}
