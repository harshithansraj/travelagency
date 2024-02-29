import java.util.ArrayList;
import java.util.List;

public class Passenger {
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
    public Passenger(String name, int passengerNumber) {
        this.name = name;
        this.passengerNumber = passengerNumber;
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
        }else if(type==PassengerType.PREMIUM){  
            return;
        }
        
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
