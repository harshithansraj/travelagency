import java.util.ArrayList;
import java.util.List;

public class Destination {
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
}
