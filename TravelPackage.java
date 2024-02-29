import java.util.ArrayList;
import java.util.List;

public class TravelPackage {
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
    public List<Passenger> getPassengers() {
        return passengers;
    }
    public List<Destination> getItinerary() {
        return itinerary;
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

    private int countPassengers(Activity activity) {
        int count = 0;
        for (Passenger passenger : passengers) {
            if (passenger.getActivities().contains(activity)) {
                count++;
            }
        }
        return count;
    }
}
