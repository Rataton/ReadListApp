package com.petitemasrata.readlistapp.model;

/**
 * Created by irata on 27/04/15.
 */
public class Event {

    String title;
    String description;
    String date;
    Venue venue;

    public Event(String title, String description, String date, Venue venue) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.venue = venue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    @Override
    public String toString() {
        return "Title: " + getTitle() +
                "\nDescription: " + getDescription() +
                "\nDate: " + getDate() +
                "\nVenue: " + getVenue();
    }
}
