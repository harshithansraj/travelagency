import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;

class Activity {
    private String name;
    private String description;
    private double cost;
    private int capacity;

    public Activity(String name, String description, double cost, int capacity) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public int getCapacity() {
        return capacity;
    }

    @Test
    public void testActivity() {
        Activity activity = new Activity("Hiking", "Enjoy a scenic hike", 50.0, 10);
        assertEquals("Hiking", activity.getName());
        assertEquals("Enjoy a scenic hike", activity.getDescription());
        assertEquals(50.0, activity.getCost(), 0.001);
        assertEquals(10, activity.getCapacity());
    }
}

class Destination {
    private String name;
    private List<Activity> activities;

    public Destination(String name) {
        this.name = name;
        activities = new ArrayList<>();
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public String getName() {
        return name;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    @Test
    public void testDestination() {
        Destination destination = new Destination("Beach Resort");
        Activity activity = new Activity("Snorkeling", "Explore underwater life", 80.0, 8);
        destination.addActivity(activity);
        assertEquals("Beach Resort", destination.getName());
        assertEquals(1, destination.getActivities().size());
        assertEquals("Snorkeling", destination.getActivities().get(0).getName());
    }
}

class Passenger {
    private String name;
    private int passengerNumber;
    private double balance;
    private PassengerType type;
    private List<Activity> activities;

    public Passenger(String name, int passengerNumber, PassengerType type) {
        this.name = name;
        this.passengerNumber = passengerNumber;
        this.type = type;
        this.activities = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getPassengerNumber() {
        return passengerNumber;
    }

    public double getBalance() {
        return balance;
    }

    public PassengerType getType() {
        return type;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
        if (type == PassengerType.STANDARD) {
            balance -= activity.getCost();
        } else if (type == PassengerType.GOLD) {
            double discountedCost = activity.getCost() * 0.9;
            balance -= discountedCost;
        }
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Test
    public void testPassenger() {
        Passenger passenger = new Passenger("John", 1, PassengerType.STANDARD);
        assertEquals("John", passenger.getName());
        assertEquals(1, passenger.getPassengerNumber());
        assertEquals(0.0, passenger.getBalance(), 0.001);
        assertEquals(PassengerType.STANDARD, passenger.getType());
        
        Activity activity = new Activity("Hiking", "Enjoy a scenic hike", 50.0, 10);
        passenger.addActivity(activity);
        assertEquals(1, passenger.getActivities().size());
    }
}

enum PassengerType {
    STANDARD, GOLD, PREMIUM
}

class TravelPackage {
    private String name;
    private int passengerCapacity;
    private List<Passenger> passengers;
    private List<Destination> itinerary;

    public TravelPackage(String name, int passengerCapacity) {
        this.name = name;
        this.passengerCapacity = passengerCapacity;
        this.passengers = new ArrayList<>();
        this.itinerary = new ArrayList<>();
    }

    public void addDestination(Destination destination) {
        itinerary.add(destination);
    }

    public void addPassenger(Passenger passenger) {
        if (passengers.size() < passengerCapacity) {
            passengers.add(passenger);
        } else {
            System.out.println("Cannot add passenger. Package full.");
        }
    }

    public void printItinerary() {
        System.out.println("Travel Package: " + name);
        for (Destination destination : itinerary) {
            System.out.println("Destination: " + destination.getName());
            for (Activity activity : destination.getActivities()) {
                System.out.println("Activity: " + activity.getName());
                System.out.println("Description: " + activity.getDescription());
                System.out.println("Cost: " + activity.getCost());
                System.out.println("Capacity: " + activity.getCapacity());
            }
        }
    }

    public void printPassengerList() {
        System.out.println("Travel Package: " + name);
        System.out.println("Passenger Capacity: " + passengerCapacity);
        System.out.println("Number of Passengers: " + passengers.size());
        for (Passenger passenger : passengers) {
            System.out.println("Name: " + passenger.getName() + ", Number: " + passenger.getPassengerNumber());
        }
    }

    public void printPassengerDetails(Passenger passenger) {
        System.out.println("Passenger Details:");
        System.out.println("Name: " + passenger.getName());
        System.out.println("Number: " + passenger.getPassengerNumber());
        if (passenger.getType() == PassengerType.STANDARD || passenger.getType() == PassengerType.GOLD) {
            System.out.println("Balance: " + passenger.getBalance());
        }
        System.out.println("Activities:");
        for (Activity activity : passenger.getActivities()) {
            System.out.println("Activity: " + activity.getName());
            System.out.println("Destination: " + findDestination(activity).getName());
            if (passenger.getType() == PassengerType.STANDARD || passenger.getType() == PassengerType.GOLD) {
                System.out.println("Price Paid: " + calculatePrice(passenger, activity));
            }
        }
    }

    public void printAvailableActivities() {
        System.out.println("Available Activities:");
        for (Destination destination : itinerary) {
            for (Activity activity : destination.getActivities()) {
                int availableSpaces = activity.getCapacity() - countPassengers(activity);
                if (availableSpaces > 0) {
                    System.out.println("Activity: " + activity.getName() + ", Available Spaces: " + availableSpaces);
                }
            }
        }
    }

    private Destination findDestination(Activity activity) {
        for (Destination destination : itinerary) {
            if (destination.getActivities().contains(activity)) {
                return destination;
            }
        }
        return null;
    }

    private int countPassengers(Activity activity) {
        int count = 0;
        for (Passenger passenger : passengers) {
            if (passenger.getActivities().contains(activity)) {
                count++;
            }
        }
        return count;
    }

    private double calculatePrice(Passenger passenger, Activity activity) {
        if (passenger.getType() == PassengerType.STANDARD) {
            return activity.getCost();
        } else if (passenger.getType() == PassengerType.GOLD) {
            return activity.getCost() * 0.9;
        } else {
            return 0.0; 
        }
    }

    @Test
    public void testTravelPackage() {
        TravelPackage travelPackage = new TravelPackage("Test Package", 2);
        Destination destination = new Destination("Beach Resort");
        Activity activity = new Activity("Snorkeling", "Explore underwater life", 80.0, 8);
        destination.addActivity(activity);
        travelPackage.addDestination(destination);
        
        assertEquals(1, travelPackage.getItinerary().size());
        assertEquals("Test Package", travelPackage.getName());
        assertEquals(2, travelPackage.getPassengerCapacity());
        
        Passenger passenger1 = new Passenger("John", 1, PassengerType.STANDARD);
        Passenger passenger2 = new Passenger("Alice", 2, PassengerType.GOLD);
        
        travelPackage.addPassenger(passenger1);
        travelPackage.addPassenger(passenger2);
        assertEquals(2, travelPackage.getPassengers().size());
    }
}

public class Main {
    public static void main(String[] args) {
    }
}
