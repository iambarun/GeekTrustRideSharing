package com.example.geektrust.person;

import com.example.geektrust.Point;

public class Driver {
    private final Entity entity;
    private boolean available;

    public Driver(String id, Point location) {
        this.entity = new Entity(id, location);
        this.available = true;
    }

    public String getId() {
        return entity.getId();
    }
    public Point getLocation() {
        return entity.getLocation();
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
}