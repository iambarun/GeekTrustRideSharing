package com.example.geektrust;

import com.example.geektrust.person.Rider;

import java.util.HashMap;
import java.util.Map;

class RiderManager {
    private final Map<String, Rider> riders = new HashMap<>();

    public void addRider(Rider rider) {
        riders.put(rider.getId(), rider);
    }

    public Rider getRider(String id) {
        return riders.get(id);
    }
}