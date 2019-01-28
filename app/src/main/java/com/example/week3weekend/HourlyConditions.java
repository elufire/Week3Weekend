package com.example.week3weekend;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.week3weekend.forecast.ForecastResponse;
import com.example.week3weekend.forecast.List;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;


public class HourlyConditions extends Fragment {
    RecyclerView rvView;
    RecyclerViewAdapter rvAdapter;

    public HourlyConditions() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hourly_conditions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvView = view.findViewById(R.id.rvView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvView.setLayoutManager(layoutManager);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void forecastEvent(ForecastResponseEvent forecastResponseEvent){
        ForecastResponse forecastResponse = forecastResponseEvent.getForecastResponse();
        ArrayList<List> forecastArrayList = new ArrayList<>(forecastResponse.getList());
        System.out.println(forecastArrayList.toString());
        rvAdapter = new RecyclerViewAdapter(forecastArrayList);

        rvView.setAdapter(rvAdapter);

    }




}
