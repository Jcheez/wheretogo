package com.wheretogo.backend.dto;

public class Place {
    private String address;
    private String nearestMRT;
    private double latitude;
    private double longitude;

    public Place(String address, String nearestMRT, double latitude, double longitude) {
        this.address = address;
        this.nearestMRT = nearestMRT;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNearestMRT() {
        return nearestMRT;
    }

    public void setNearestMRT(String nearestMRT) {
        this.nearestMRT = nearestMRT;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
