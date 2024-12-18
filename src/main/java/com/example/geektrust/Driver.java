package com.example.geektrust;

class Driver {
    private final String id;
    private final Point location;
    private boolean available;

    Driver(String id, Point location) {
        this.id = id;
        this.location = location;
        this.available = true;
    }

    public String getId() { return id; }
    public Point getLocation() { return location; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}