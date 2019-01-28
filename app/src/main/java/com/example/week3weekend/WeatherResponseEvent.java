package com.example.week3weekend;

import com.example.week3weekend.weatherdata.WeatherResponse;

public class WeatherResponseEvent {
    WeatherResponse weatherResponse;

    public WeatherResponseEvent(WeatherResponse weatherResponse) {
        this.weatherResponse = weatherResponse;
    }

    public WeatherResponse getWeatherResponse() {
        return weatherResponse;
    }

    public void setWeatherResponse(WeatherResponse weatherResponse) {
        this.weatherResponse = weatherResponse;
    }
}
