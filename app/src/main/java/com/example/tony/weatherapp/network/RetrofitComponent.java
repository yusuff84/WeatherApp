package com.example.tony.weatherapp.network;


import com.example.tony.weatherapp.interactor.interactorcity.intercatorweather.InteractorWeatherImpl;

import dagger.Component;

@Component(modules = {RetrofitModule.class})
public interface RetrofitComponent {
    void injectInteractorWeather(InteractorWeatherImpl interactorWeather);
}
