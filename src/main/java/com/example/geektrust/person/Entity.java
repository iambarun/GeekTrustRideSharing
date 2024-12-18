package com.example.geektrust.person;

import com.example.geektrust.Point;

public class Entity {
    private final String id;
    private final Point location;

    public Entity(String id, Point location) {
        this.id = id;
        this.location = location;
    }

    public String getId() { return id; }
    public Point getLocation() { return location; }
}
