package com.example.doa;

public class Scheme {
    private String name;
    private int image;
    private String videoUrl;
    private String description;
    private String eligibilityContent;
    private String howToApplyContent;
    private String category;  // New category field
    private int logoResource; // New field for scheme logo


    // Constructor with category
    public Scheme(String name, int image, String videoUrl, String description,
                  String eligibilityContent, String howToApplyContent, String category, int logoResource) {
        this.name = name;
        this.image = image;
        this.videoUrl = videoUrl;
        this.description = description;
        this.eligibilityContent = eligibilityContent;
        this.howToApplyContent = howToApplyContent;
        this.category = category;
        this.logoResource = logoResource; // Initialize logo

    }

    // Getters for all fields
    public String getName() { return name; }
    public int getImage() { return image; }
    public String getVideoUrl() { return videoUrl; }
    public String getDescription() { return description; }
    public String getEligibilityContent() { return eligibilityContent; }
    public String getHowToApplyContent() { return howToApplyContent; }
    public String getCategory() { return category; }
    public int getLogoResource() { return logoResource; }
}
