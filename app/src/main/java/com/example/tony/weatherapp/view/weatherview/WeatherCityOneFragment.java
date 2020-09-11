package com.example.tony.weatherapp.view.weatherview;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tony.weatherapp.R;
import com.example.tony.weatherapp.adapters.WeatherAdapter.WeatherAdapter;
import com.example.tony.weatherapp.model.modelweather.WeatherCities;
import com.example.tony.weatherapp.presenter.presenterweather.WeatherPresenter;
import com.example.tony.weatherapp.presenter.presenterweather.WeatherPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WeatherCityOneFragment extends Fragment implements WeatherCityView {

    @BindView(R.id.recycler_view_weather)
    RecyclerView recyclerViewWeather;


    String city;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_city_one, container, false);
        ButterKnife.bind(this, view);

        WeatherActivity weatherActivity = (WeatherActivity) getActivity();


        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        //recyclerViewWeather.setHasFixedSize(true);

        recyclerViewWeather.setLayoutManager(linearLayoutManager);


        WeatherPresenter weatherPresenter = new WeatherPresenterImpl(this);
        weatherPresenter.onValidateWeather(city);

        return view;
    }


    @Override
    public void showWeather(WeatherCities weatherCities) {
        recyclerViewWeather.setAdapter(new WeatherAdapter(this, weatherCities));
    }


    public void setCityName(String nameCity) {
        city = nameCity;
    }


}
