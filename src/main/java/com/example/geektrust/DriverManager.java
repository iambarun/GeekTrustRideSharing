package com.example.geektrust;

import com.example.geektrust.person.Driver;

import java.util.*;

class DriverManager {
    private final Map<String, Driver> drivers = new HashMap<>();

    public void addDriver(Driver driver) {
        drivers.put(driver.getId(), driver);
    }

    public Driver getDriver(String id) {
        return drivers.get(id);
    }

    public List<Driver> findDriversWithinDistance(Point location, double maxDistance) {
        List<Driver> result = new ArrayList<>();
        for (Driver driver : drivers.values()) {
            if (driver.isAvailable() && Point.calculateDistance(location, driver.getLocation()) <= maxDistance) {
                result.add(driver);
            }
        }
        result.sort(Comparator.comparingDouble(d -> Point.calculateDistance(location, d.getLocation())));
        return result;
    }
}