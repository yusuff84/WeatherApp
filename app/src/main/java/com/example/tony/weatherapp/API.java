package com.example.tony.weatherapp;

import com.example.tony.weatherapp.model.modelcities.CitiesModel;
import com.example.tony.weatherapp.model.modelweather.WeatherCities;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface API {

    @GET("cities.json")
    Call<CitiesModel> getModelCity(@Query("country") String state,
                                   @Query("key") String key);

    @GET("forecast")
    Call<WeatherCities> getWeatherCities(@Query("q") String country,
                                         @Query("appid") String appId);


}
