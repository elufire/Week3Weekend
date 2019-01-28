package com.example.week3weekend;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.week3weekend.weatherdata.WeatherResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class CurrentConditions extends Fragment {
    TextView tvLocation;
    TextView tvZip;
    TextView tvTitle;
    TextView tvTemp;
    ImageView ivWeather;
    EditText etZipCode;

    private OnFragmentInteractionListener mListener;

    public CurrentConditions() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_current_conditions, container, false);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle = view.findViewById(R.id.tvCurrConds);
        ivWeather= view.findViewById(R.id.weatherPicView);
        etZipCode = view.findViewById(R.id.etZip);
        tvTemp = view.findViewById(R.id.tvTemp);
        tvLocation = view.findViewById(R.id.tvLocation);
        tvZip = view.findViewById(R.id.tvZipCode);
        Glide.with(view.getContext()).load("https://cdn.pixabay.com/photo/2017/01/29/11/09/rain-2017532_960_720.png").into(ivWeather);
        Button zipEnter = view.findViewById(R.id.zipButton);
        zipEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.valueOf(etZipCode.getText().toString()) >=501 && Integer.valueOf(etZipCode.getText().toString()) <=99950 &&
                etZipCode.getText().toString().length() == 5){
                    System.out.println(etZipCode.getText().toString());
                    tvZip.setText(etZipCode.getText().toString());
                    OkHttpHelper.ascyncOkHttpApi(etZipCode.getText().toString());
                  }
                etZipCode.setText("");


            }
        });
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void weatherEvent(WeatherResponseEvent weatherResponseEvent){
        WeatherResponse weatherResponse = weatherResponseEvent.getWeatherResponse();
        tvLocation.setText(weatherResponse.getName());
        int temp = toFarenheit(weatherResponse.getMain().getTemp());
        if(temp <60){
            tvTemp.setTextColor(Color.parseColor("#0a9ad8"));
        }
        else {

            tvTemp.setTextColor(Color.parseColor("#d61c0e"));
        }
        tvTemp.setText(Integer.toString(temp));
        String description = weatherResponse.getWeather().get(0).getDescription();
        System.out.println(description);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        int hour = Integer.valueOf(sdf.format(cal.getTime()));
        if(description.equals("broken clouds")|| description.equals("few clouds")|| description.equals("scattered clouds")){
            if(hour<6 || hour>18){
                Glide.with(getContext()).load("https://i.imgur.com/Wbe5odz.jpg").into(ivWeather);

            }else{
                Glide.with(getContext()).load("https://cdn3.iconfinder.com/data/icons/stylized-weather-icons/745/803BrokenClouds.png").into(ivWeather);

            }

        }
        else if(description.equals("clear sky")){
            if(hour<6 || hour>18){
                Glide.with(getContext()).load("https://d2v9y0dukr6mq2.cloudfront.net/video/thumbnail/S15GBCm/videoblocks-cute-cartoon-character-of-smiling-moon-with-sleeping-hat-background-and-colorful-stars-rotation-animation-of-happy-moon-seamless-loop-background-for-children-or-babies-full-hd-and-4k_s2gkiqrox_thumbnail-full01.png").into(ivWeather);

            }else{
                Glide.with(getContext()).load("https://ih0.redbubble.net/image.82989265.0722/st%2Csmall%2C215x235-pad%2C210x230%2Cf8f8f8.lite-1u4.jpg").into(ivWeather);

            }

        }
        else{
            Glide.with(this).load("https://www.bedfordny.gov/wp-content/uploads/2018/07/sunshine-sun-clip-art-with-transparent-background-free-free-clipart-sun-2361_2358-2.png").into(ivWeather);
        }
    }

    public static int toFarenheit(Float kelvin){
        return (int)(kelvin - 273.15)*9/5+32;
    }
}
