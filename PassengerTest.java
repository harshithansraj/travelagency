import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class PassengerTest {

    @Test
    public void testPassengerConstructor() {
        Passenger passenger = new Passenger("John", 1, PassengerType.STANDARD);
        assertNotNull(passenger);
        assertEquals("John", passenger.getName());
        assertEquals(1, passenger.getPassengerNumber());
        assertEquals(PassengerType.STANDARD, passenger.getType());
        assertTrue(passenger.getActivities().isEmpty());
    }

    
}
