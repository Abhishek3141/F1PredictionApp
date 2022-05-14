package com.abhi.f1infoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverRowHolder> {

    ArrayList<Driver> DriverData;
    ArrayList<Note> NoteData;
    Context context;
    MyClickInterface myClickInterface;

    public DriverAdapter(ArrayList<Driver> DriverData,ArrayList<Note> NoteData,Context context, MyClickInterface myClickInterface){
        this.context = context;
        this.DriverData = DriverData;
        this.NoteData = NoteData;
        this.myClickInterface = myClickInterface;
    }

    @NonNull
    @Override
    public DriverRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.driver_row,parent,false);

        return new DriverRowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverRowHolder holder, int position) {
        holder.txtDriverName.setText(DriverData.get(position).getName());
        holder.imgDriver.setImageResource(DriverData.get(position).getImage());
        holder.txtPred.setText(Integer.toString(DriverData.get(position).getPrediction()));

    }

    @Override
    public int getItemCount() {
        return DriverData.size();
    }

    class DriverRowHolder extends RecyclerView.ViewHolder{

        TextView txtDriverName;
        ImageView imgDriver;
        TextView txtPred;

        public DriverRowHolder(@NonNull View itemView) {
            super(itemView);

            txtDriverName = itemView.findViewById(R.id.txt_Driver_name);
            imgDriver = itemView.findViewById(R.id.img_Driver);
            txtPred = itemView.findViewById(R.id.home_prediction);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myClickInterface.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    interface MyClickInterface{
        void onItemClick(int positionOfTheDriver);
    }


}
