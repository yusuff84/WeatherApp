package com.example.tony.weatherapp.network;

import dagger.Module;
import dagger.Provides;


@Module
public class RetrofitModule {
    @Provides
    public MyRetrofit getRetrofit() {
        return new MyRetrofit();
    }
}
