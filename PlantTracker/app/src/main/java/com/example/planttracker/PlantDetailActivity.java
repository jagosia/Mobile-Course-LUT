package com.example.planttracker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PlantDetailActivity extends AppCompatActivity {

    private int waterAmount = 0;
    private Plant plant;
    private TextView waterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_detail);

        int index = getIntent().getIntExtra("plant_index", -1);
        if (index == -1 || index >= MainActivity.plantList.size()) {
            Toast.makeText(this, "Invalid plant index", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        plant = MainActivity.plantList.get(index);
        waterAmount = plant.getWaterAmount();

        ImageView plantImageView = findViewById(R.id.plantImageView);
        TextView nameTextView = findViewById(R.id.plantNameDetailTextView);
        TextView descTextView = findViewById(R.id.plantDescriptionDetailTextView);
        waterTextView = findViewById(R.id.wateredAmountTextView);
        EditText inputWaterEditText = findViewById(R.id.inputWaterEditText);
        Button waterButton = findViewById(R.id.waterButton);
        Button wikiButton = findViewById(R.id.wikiButton);

        nameTextView.setText(plant.getName());
        descTextView.setText(plant.getDescription());
        plantImageView.setImageResource(plant.getImageResId());
        updateWaterText();

        waterButton.setOnClickListener(v -> {
            String input = inputWaterEditText.getText().toString();
            if (!input.isEmpty()) {
                int value = Integer.parseInt(input);
                waterAmount += value;
                plant.setWaterAmount(waterAmount);
                updateWaterText();
                inputWaterEditText.setText("");
            }
        });

        wikiButton.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(plant.getWikiUrl()));
            startActivity(browserIntent);
        });
    }

    private void updateWaterText() {
        waterTextView.setText("Watered: " + waterAmount + " ml");
    }
}
