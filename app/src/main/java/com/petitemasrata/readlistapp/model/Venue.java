package com.petitemasrata.readlistapp.model;

/**
 * Created by irata on 27/04/15.
 */
public class Venue {

    String name;
    String address;
    String city;
    String region;
    String country;

    public Venue(String name, String address, String city, String region, String country) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.region = region;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return getName() +
                "\n" + getAddress() +
                ", " + getCity() +
                ", " + getRegion() +
                ", " + getCountry();
    }
}
