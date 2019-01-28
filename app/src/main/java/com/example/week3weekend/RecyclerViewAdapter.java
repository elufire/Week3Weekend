package com.example.week3weekend;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.week3weekend.forecast.List;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    ArrayList<List> listsArrayList;
    String TAG;

    public RecyclerViewAdapter(ArrayList<List> listsArrayList) {
        this.listsArrayList = listsArrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        List list = listsArrayList.get(position);

        if (list != null) {
            int temp = toFarenheit(list.getMain().getTemp());
            String time = list.getDtTxt();
//            String name = list.getName();
//            String fullName = list.getFullName();
//            viewHolder.setlist(list);
            if(temp <60){
                viewHolder.tvTemp.setTextColor(Color.parseColor("#0a9ad8"));
            }
            else{

                viewHolder.tvTemp.setTextColor(Color.parseColor("#d61c0e"));
            }
            viewHolder.tvTemp.setText(String.valueOf(temp));
            viewHolder.tvTime.setText(time);
//            viewHolder.tvFullName.setText(fullName);
        }
    }

    @Override
    public int getItemCount() {
        return listsArrayList != null ? listsArrayList.size() : 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTemp;
        TextView tvTime;


        List itemlist;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tvTemp = itemView.findViewById(R.id.tvHourTemp);
            tvTime = itemView.findViewById(R.id.tvTime);

        }
    }
        public static int toFarenheit(Float kelvin) {
            return (int) (kelvin - 273.15) * 9 / 5 + 32;
        }

}