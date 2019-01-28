package com.example.week3weekend;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements CurrentConditions.OnFragmentInteractionListener{
    private static final String Frag_Tag_One = "frag_current";
    private static final String Frag_Tag_Two = "frag_hourly";
    FragmentManager weatherFrameManager;
    CurrentConditions currentConditions;
    HourlyConditions hourlyConditions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentConditions = new CurrentConditions();
        hourlyConditions = new HourlyConditions();

        weatherFrameManager = getSupportFragmentManager();
        weatherFrameManager.beginTransaction().replace(R.id.flFlagPlaceholderOne, currentConditions)
                .addToBackStack(Frag_Tag_One).commit();
        weatherFrameManager.beginTransaction().replace(R.id.flFlagPlaceholderTwo, hourlyConditions)
                .addToBackStack(Frag_Tag_Two).commit();

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
