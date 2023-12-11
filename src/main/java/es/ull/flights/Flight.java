/**
 * @file Flight.java
 * @brief This file contains the implementation of the Flight class.
 *
 * The Flight class represents a flight with a specific flight number and a
 * certain number of seats. It manages the addition and removal of passengers.
 * The flight number must follow a specific pattern defined by a regular expression.
 *
 */

package es.ull.flights;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.ull.passengers.Passenger;

/**
 * @class Flight
 * @brief Represents a flight with specific attributes and functionalities.
 *
 * The Flight class includes methods for getting the flight number, the number
 * of passengers, adding a passenger to the flight, and removing a passenger
 * from the flight.
 */
public class Flight {

    private String flightNumber; ///< The flight number.
    private int seats; ///< The total number of seats in the flight.
    private Set<Passenger> passengers = new HashSet<>(); ///< Set of passengers on the flight.

    private static String flightNumberRegex = "^[A-Z]{2}\\d{3,4}$"; ///< Regular expression for validating flight numbers.
    private static Pattern pattern = Pattern.compile(flightNumberRegex); ///< Pattern object for flight number validation.

    /**
     * @brief Constructor for the Flight class.
     *
     * Initializes a new Flight object with the specified flight number and seats.
     *
     * @param flightNumber The flight number to set for the flight.
     * @param seats The total number of seats available in the flight.
     * @throws RuntimeException if the provided flight number is invalid.
     */
    public Flight(String flightNumber, int seats) {
        Matcher matcher = pattern.matcher(flightNumber);
        if (!matcher.matches()) {
            throw new RuntimeException("Invalid flight number");
        }
        this.flightNumber = flightNumber;
        this.seats = seats;
    }

    /**
     * @brief Getter for the flight number.
     *
     * @return The flight number.
     */
    public String getFlightNumber() {
        return flightNumber;
    }

    /**
     * @brief Getter for the number of passengers on the flight.
     *
     * @return The number of passengers on the flight.
     */
    public int getNumberOfPassengers() {
        return passengers.size();
    }

    /**
     * @brief Adds a passenger to the flight.
     *
     * This method adds the specified passenger to the flight if there are
     * available seats. Otherwise, it throws a RuntimeException.
     *
     * @param passenger The passenger to be added to the flight.
     * @return true if the passenger is added successfully, false otherwise.
     * @throws RuntimeException if there are not enough seats for the flight.
     */
    public boolean addPassenger(Passenger passenger) {
        if (getNumberOfPassengers() >= seats) {
            throw new RuntimeException("Not enough seats for flight " + getFlightNumber());
        }
        passenger.setFlight(this);
        return passengers.add(passenger);
    }

    /**
     * @brief Removes a passenger from the flight.
     *
     * This method removes the specified passenger from the flight and updates
     * the passenger's flight association to null.
     *
     * @param passenger The passenger to be removed from the flight.
     * @return true if the passenger is removed successfully, false otherwise.
     */
    public boolean removePassenger(Passenger passenger) {
        passenger.setFlight(null);
        return passengers.remove(passenger);
    }
}
