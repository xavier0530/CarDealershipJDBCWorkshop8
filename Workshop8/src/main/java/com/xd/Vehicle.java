package com.xd;

public class Vehicle {
    private int vehicleID;
    private int price;
    private  int year;
    private String color;
    private int mileage;
    private String vehicleType;

    public Vehicle(int vehicleID, int price, int year, String color, int mileage, String vehicleType) {
        this.vehicleID = vehicleID;
        this.price = price;
        this.year = year;
        this.color = color;
        this.mileage = mileage;
        this.vehicleType = vehicleType;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
}
