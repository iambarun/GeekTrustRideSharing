package com.example.geektrust;

import com.example.geektrust.person.Driver;
import com.example.geektrust.person.Rider;

import java.util.List;

class RideSharingService {
    private static final double BASE_FARE = 50.0;
    private static final double FARE_PER_KM = 6.5;
    private static final double FARE_PER_MIN = 2.0;
    private static final double SERVICE_TAX = 0.2;
    private static final double MAX_DISTANCE = 5.0;
    private static final int MAX_MATCHES = 5;

    private final DriverManager driverManager = new DriverManager();
    private final RiderManager riderManager = new RiderManager();
    private final RideManager rideManager = new RideManager();

    public void addDriver(String id, int x, int y) {
        driverManager.addDriver(new Driver(id, new Point(x, y)));
    }

    public void addRider(String id, int x, int y) {
        riderManager.addRider(new Rider(id, new Point(x, y)));
    }

    public void match(String riderId) {
        Rider rider = riderManager.getRider(riderId);
        if (rider == null) {
            System.out.println("NO_DRIVERS_AVAILABLE");
            return;
        }

        List<Driver> availableDrivers = driverManager.findDriversWithinDistance(rider.getLocation(), MAX_DISTANCE);
        if (availableDrivers.isEmpty()) {
            System.out.println("NO_DRIVERS_AVAILABLE");
        } else {
            System.out.print("DRIVERS_MATCHED");
            for (int i = 0; i < Math.min(MAX_MATCHES, availableDrivers.size()); i++) {
                System.out.print(" " + availableDrivers.get(i).getId());
            }
            System.out.println();
        }
    }

    public void startRide(String rideId, int n, String riderId) {
        Rider rider = riderManager.getRider(riderId);
        if (rider == null || rideManager.rideExists(rideId)) {
            System.out.println("INVALID_RIDE");
            return;
        }

        List<Driver> availableDrivers = driverManager.findDriversWithinDistance(rider.getLocation(), MAX_DISTANCE);
        if (n < 1 || n > availableDrivers.size()) {
            System.out.println("INVALID_RIDE");
            return;
        }

        Driver selectedDriver = availableDrivers.get(n - 1);
        selectedDriver.setAvailable(false);
        rideManager.startRide(new Ride(rideId, riderId, selectedDriver.getId()));
        System.out.println("RIDE_STARTED " + rideId);
    }

    public void stopRide(String rideId, int destX, int destY, int timeTaken) {
        Ride ride = rideManager.getRide(rideId);
        if (ride == null || ride.isCompleted()) {
            System.out.println("INVALID_RIDE");
            return;
        }

        Rider rider = riderManager.getRider(ride.getRiderId());
        double distance = Point.calculateDistance(rider.getLocation(), new Point(destX, destY));
        double fare = calculateFare(distance, timeTaken);
        ride.complete(fare);
        driverManager.getDriver(ride.getDriverId()).setAvailable(true);
        System.out.println("RIDE_STOPPED " + rideId);
    }

    public void bill(String rideId) {
        Ride ride = rideManager.getRide(rideId);
        if (ride == null) {
            System.out.println("INVALID_RIDE");
        } else if (!ride.isCompleted()) {
            System.out.println("RIDE_NOT_COMPLETED");
        } else {
            System.out.printf("BILL %s %s %.2f%n", ride.getRideId(), ride.getDriverId(), ride.getBill());
        }
    }

    private double calculateFare(double distance, int timeTaken) {
        double amount = BASE_FARE + (FARE_PER_KM * distance) + (FARE_PER_MIN * timeTaken);
        return Math.round((amount + amount * SERVICE_TAX) * 100.0) / 100.0;
    }

    public static void main(String[] args) {
        RideSharingService service = new RideSharingService();
        service.addDriver("D1", 1, 1);
        service.addDriver("D2", 4, 5);
        service.addDriver("D3", 2, 2);
        service.addRider("R1", 0, 0);
        service.match("R1");
        service.startRide("RIDE-001", 2, "R1");
        service.stopRide("RIDE-001", 4, 5, 32);
        service.bill("RIDE-001");
    }
}
