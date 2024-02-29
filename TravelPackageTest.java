import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TravelPackageTest {

    private TravelPackage travelPackage;

    @Before
    public void setUp() {
        travelPackage = new TravelPackage("Summer Adventure", 10);
    }

    @Test
    public void testAddPassenger() {
        Passenger passenger = new Passenger("Alice", 1);
        travelPackage.addPassenger(passenger);
        assertEquals(1, travelPackage.getPassengers().size());
    }

    @Test
    public void testAddDestination() {
        Destination destination = new Destination("Beach Paradise");
        travelPackage.addDestination(destination);
        assertEquals(1, travelPackage.getItinerary().size());
    }

}
