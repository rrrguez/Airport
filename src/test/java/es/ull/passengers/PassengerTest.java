package es.ull.passengers;

import es.ull.flights.Flight;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class PassengerTest {
    @Test
    public void testGetters() {
        Passenger passenger = new Passenger("ID123", "John Doe", "US");
        assertEquals("ID123", passenger.getIdentifier());
        assertEquals("John Doe", passenger.getName());
        assertEquals("US", passenger.getCountryCode());
    }

    @Test
    public void testInvalidCountryCode() {
        assertThrows(RuntimeException.class, () -> new Passenger("ID123", "John Doe", "Invalid"));
    }

    @Test
    public void testJoinFlight() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger = new Passenger("ID123", "John Doe", "US");
        passenger.joinFlight(flight);
        assertEquals(flight, passenger.getFlight());
        assertEquals(1, flight.getNumberOfPassengers());
    }

    @Test
    public void testLeavePreviousFlight() {
        Flight flight1 = new Flight("AB123", 2);
        Flight flight2 = new Flight("CD456", 2);
        Passenger passenger = new Passenger("ID123", "John Doe", "US");
        passenger.joinFlight(flight1);
        passenger.joinFlight(flight2);
        assertEquals(flight2, passenger.getFlight());
        assertEquals(0, flight1.getNumberOfPassengers());
        assertEquals(1, flight2.getNumberOfPassengers());
    }

    @Test
    public void testJoinFlightFails() {
        Flight flight1 = new Flight("AB123", 1);
        Passenger passenger1 = new Passenger("ID123", "John Doe", "US");
        Passenger passenger2 = new Passenger("ID456", "Jane Doe", "CA");
        passenger1.joinFlight(flight1);
        assertThrows(RuntimeException.class, () -> passenger2.joinFlight(flight1));
    }

}
