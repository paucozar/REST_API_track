package com.example.retrofitrecyclerview.model;

// Track.java
public class Track {
    private int id;
    private String title;
    private String artist;

    // Constructor
    public Track(int id, String title, String artist) {
        this.id = id;
        this.title = title;
        this.artist = artist;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
