package com.example.geektrust.person;

import com.example.geektrust.Point;

public class Rider {
    private final Entity entity;

    public Rider(String id, Point location) {
        this.entity = new Entity(id, location);
    }

    public String getId() {
        return entity.getId();
    }
    public Point getLocation() {
        return entity.getLocation();
    }
}
