import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class DestinationTest {

    @Test
    public void testDestinationConstructor() {
        Destination destination = new Destination("Beach Resort");
        assertNotNull(destination);
        assertEquals("Beach Resort", destination.getName());
        assertTrue(destination.getActivities().isEmpty());
    }

    @Test
    public void testAddActivity() {
        Destination destination = new Destination("Mountain Retreat");
        Activity hiking = new Activity("Hiking", "Enjoy a scenic hike", 50.0, 10);
        destination.addActivity(hiking);
        List<Activity> activities = destination.getActivities();
        assertEquals(1, activities.size());
        assertEquals(hiking, activities.get(0));
    }

}
