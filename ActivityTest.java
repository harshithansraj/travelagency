import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.*;
public class ActivityTest {

    @Test
    public void testActivityConstructor() {
        Activity activity = new Activity("Hiking", "Enjoy a scenic hike", 50.0, 10);
        assertEquals("Hiking", activity.getName());
        assertEquals("Enjoy a scenic hike", activity.getDescription());
        assertEquals(50.0, activity.getCost(), 0.01);
        assertEquals(10, activity.getCapacity());
    }
}
