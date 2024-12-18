package com.example.geektrust;

class Rider {
    private final String id;
    private final Point location;

    Rider(String id, Point location) {
        this.id = id;
        this.location = location;
    }

    public String getId() { return id; }
    public Point getLocation() { return location; }
}
