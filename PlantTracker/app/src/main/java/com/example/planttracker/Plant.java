package com.example.planttracker;

public class Plant {
    private String name;
    private String description;
    private int waterAmount;
    private int imageResId;
    private String wikiUrl;

    public Plant(String name, String description, int waterAmount, int imageResId, String wikiUrl) {
        this.name = name;
        this.description = description;
        this.waterAmount = waterAmount;
        this.imageResId = imageResId;
        this.wikiUrl = wikiUrl;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getWaterAmount() { return waterAmount; }
    public int getImageResId() { return imageResId; }
    public String getWikiUrl() { return wikiUrl; }

    public void addWater(int ml) {
        waterAmount += ml;
    }
}



