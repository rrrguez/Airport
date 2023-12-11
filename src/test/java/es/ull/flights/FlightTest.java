package es.ull.flights;

import es.ull.passengers.Passenger;

import org.junit.Test;

import static org.junit.Assert.*;

public class FlightTest {

    @Test
    public void testValidFlightNumber() {
        Flight flight = new Flight("AB123", 100);
        assertEquals("AB123", flight.getFlightNumber());
    }

    @Test
    public void testInvalidFlightNumber() {
        assertThrows(RuntimeException.class, () -> new Flight("Invalid", 100));
    }

    @Test
    public void testAddPassenger() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger = new Passenger("ID123", "John Doe", "US");
        assertTrue(flight.addPassenger(passenger));
        assertEquals(1, flight.getNumberOfPassengers());
    }

    @Test
    public void testAddPassengerExceedsSeats() {
        Flight flight = new Flight("AB123", 1);
        Passenger passenger1 = new Passenger("ID123", "John Doe", "US");
        Passenger passenger2 = new Passenger("ID456", "Jane Doe", "CA");
        flight.addPassenger(passenger1);
        assertThrows(RuntimeException.class, () -> flight.addPassenger(passenger2));
    }

    @Test
    public void testRemovePassenger() {
        Flight flight = new Flight("AB123", 2);
        Passenger passenger = new Passenger("ID123", "John Doe", "US");
        flight.addPassenger(passenger);
        assertTrue(flight.removePassenger(passenger));
        assertEquals(0, flight.getNumberOfPassengers());
    }

    @Test
    public void testMixedScenario() {
        // Create flights
        Flight flight1 = new Flight("AB123", 2);
        Flight flight2 = new Flight("CD456", 1);

        // Create passengers
        Passenger passenger1 = new Passenger("ID123", "John Doe", "US");
        Passenger passenger2 = new Passenger("ID456", "Jane Doe", "CA");
        Passenger passenger3 = new Passenger("ID789", "Alice Smith", "GB");

        // Join passengers to flights
        passenger1.joinFlight(flight1);
        passenger2.joinFlight(flight1);

        // Ensure passengers are in the correct flights
        assertEquals(flight1, passenger1.getFlight());
        assertEquals(flight1, passenger2.getFlight());
        assertNull(passenger3.getFlight());

        // Ensure the number of passengers in each flight is correct
        assertEquals(2, flight1.getNumberOfPassengers());
        assertEquals(0, flight2.getNumberOfPassengers());

        // Try to add a passenger to a full flight
        passenger1.joinFlight(flight2);
        assertThrows(RuntimeException.class, () -> flight2.addPassenger(passenger3));
        passenger1.joinFlight(flight1);

        // Remove a passenger from a flight
        assertTrue(flight1.removePassenger(passenger1));
        assertNull(passenger1.getFlight());
        assertEquals(1, flight1.getNumberOfPassengers());
    }
}


