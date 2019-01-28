package com.example.week3weekend;

import com.example.week3weekend.forecast.ForecastResponse;

public class ForecastResponseEvent {
    ForecastResponse forecastResponse;

    public ForecastResponseEvent(ForecastResponse forecastResponse) {
        this.forecastResponse = forecastResponse;
    }

    public ForecastResponse getForecastResponse() {
        return forecastResponse;
    }

    public void setForecastResponse(ForecastResponse forecastResponse) {
        this.forecastResponse = forecastResponse;
    }
}
