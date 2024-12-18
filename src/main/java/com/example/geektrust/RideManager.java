package com.example.geektrust;

import java.util.HashMap;
import java.util.Map;

class RideManager {
    private final Map<String, Ride> rides = new HashMap<>();

    public void startRide(Ride ride) {
        rides.put(ride.getRideId(), ride);
    }

    public boolean rideExists(String rideId) {
        return rides.containsKey(rideId);
    }

    public Ride getRide(String rideId) {
        return rides.get(rideId);
    }
}