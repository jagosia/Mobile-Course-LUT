package com.example.planttracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PlantAdapter extends BaseAdapter {

    private Context context;
    private List<Plant> plantList;

    public PlantAdapter(Context context, List<Plant> plantList) {
        this.context = context;
        this.plantList = plantList;
    }

    @Override
    public int getCount() {
        return plantList.size();
    }

    @Override
    public Object getItem(int position) {
        return plantList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.plant_list_item, parent, false);
        }

        Plant currentPlant = plantList.get(position);

        TextView nameTextView = convertView.findViewById(R.id.plantNameTextView);
        TextView descriptionTextView = convertView.findViewById(R.id.plantDescriptionTextView);
        TextView waterAmountTextView = convertView.findViewById(R.id.waterAmountTextView);


        nameTextView.setText(currentPlant.getName());
        descriptionTextView.setText(currentPlant.getDescription());
        waterAmountTextView.setText("Watered: " + currentPlant.getWaterAmount() + " ml");

        return convertView;
    }
}

