package com.example.planttracker;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private List<Plant> plantList;
    public static List<Plant> plantList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String[] names = getResources().getStringArray(R.array.plant_names);
        String[] descriptions = getResources().getStringArray(R.array.plant_descriptions);
        String[] wikiUrls = getResources().getStringArray(R.array.plant_wiki_urls);

        int[] imageResIds = {
                R.drawable.monstera,
                R.drawable.aloe_vera,
                R.drawable.cactus
        };

        plantList = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            plantList.add(new Plant(names[i], descriptions[i], 0, imageResIds[i], wikiUrls[i]));
        }

        ListView listView = findViewById(R.id.plantListView);
        PlantAdapter adapter = new PlantAdapter(this, plantList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, PlantDetailActivity.class);
                intent.putExtra("plant_index", position);
                startActivityForResult(intent, 1);
            }
        });
    }

    public static List<Plant> getPlantListStatic() {
        return new ArrayList<>();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            ((BaseAdapter) ((ListView) findViewById(R.id.plantListView)).getAdapter()).notifyDataSetChanged();
        }
    }

}