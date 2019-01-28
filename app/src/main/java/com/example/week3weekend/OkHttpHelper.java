package com.example.week3weekend;

import android.content.Context;
import android.util.Log;

import com.example.week3weekend.forecast.ForecastResponse;
import com.example.week3weekend.weatherdata.WeatherResponse;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.week3weekend.Constants.FORECAST_URL;
import static com.example.week3weekend.Constants.ZIP_URL;

public class OkHttpHelper {
    Context context;

    public OkHttpHelper(Context context) {
        this.context = context;
    }

    public static void ascyncOkHttpApi(String zipCode) {
        OkHttpClient okHttpClientCurrent = new OkHttpClient();
        OkHttpClient okHttpClientForecast = new OkHttpClient();

        String url = ZIP_URL + zipCode + ",us&appid=84804477b30c90b9327d210d284acba6";
        String url2 = FORECAST_URL + zipCode + ",us&appid=84804477b30c90b9327d210d284acba6";
        Request request = new Request.Builder()
                .url(url)
                .build();
        Request request2 = new Request.Builder()
                .url(url2)
                .build();

        okHttpClientCurrent.newCall(request).enqueue(new Callback() {
            String jsonResonse;

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                  jsonResonse = response.body().string();
                Gson gson = new Gson();
                WeatherResponse weatherResponse = gson.fromJson(jsonResonse, WeatherResponse.class);
                System.out.println(weatherResponse.getName());
                EventBus.getDefault().post(new WeatherResponseEvent(weatherResponse));
            }
        });
        okHttpClientForecast.newCall(request2).enqueue(new Callback() {
            String jsonResonse;

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("TAG", "onFailure: ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                jsonResonse = response.body().string();
                System.out.println(jsonResonse);
                Gson gson = new Gson();
                ForecastResponse forecastResponse = gson.fromJson(jsonResonse, ForecastResponse.class);
                //System.out.println(forecastResponse.getList().get(0).getMain().getTempMax());
                EventBus.getDefault().post(new ForecastResponseEvent(forecastResponse));
            }
        });
    }
}