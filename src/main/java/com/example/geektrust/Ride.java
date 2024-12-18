package com.example.geektrust;

public class Ride {

    private final String rideId;
    private final String riderId;
    private final String driverId;
    private boolean completed;
    private double bill;

    Ride(String rideId, String riderId, String driverId) {
        this.rideId = rideId;
        this.riderId = riderId;
        this.driverId = driverId;
        this.completed = false;
    }

    public String getRideId() {
        return rideId;
    }

    public String getRiderId() {
        return riderId;
    }

    public String getDriverId() {
        return driverId;
    }

    public boolean isCompleted() {
        return completed;
    }

    public double getBill() {
        return bill;
    }

    public void complete(double bill) {
        this.completed = true;
        this.bill = bill;
    }
}