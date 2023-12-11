/**
 * @file Passenger.java
 * @brief This file contains the implementation of the Passenger class.
 *
 * The Passenger class represents an individual passenger with a unique
 * identifier, name, and country code. It also manages the association with a
 * specific flight, allowing passengers to join and leave flights.
 *
 */

package es.ull.passengers;

import java.util.Arrays;
import java.util.Locale;

import es.ull.flights.Flight;

/**
 * @class Passenger
 * @brief Represents an individual passenger with associated functionalities.
 *
 * The Passenger class includes methods for getting the passenger's identifier,
 * name, country code, associated flight, joining a flight, setting the flight,
 * and generating a string representation of the passenger.
 */
public class Passenger {

    private String identifier; ///< The unique identifier of the passenger.
    private String name; ///< The name of the passenger.
    private String countryCode; ///< The country code of the passenger.
    private Flight flight; ///< The flight associated with the passenger.

    /**
     * @brief Constructor for the Passenger class.
     *
     * Initializes a new Passenger object with the specified identifier, name,
     * and country code. It validates the provided country code against the
     * ISO country codes.
     *
     * @param identifier The unique identifier for the passenger.
     * @param name The name of the passenger.
     * @param countryCode The country code of the passenger.
     * @throws RuntimeException if the provided country code is invalid.
     */
    public Passenger(String identifier, String name, String countryCode) {
        if (!Arrays.asList(Locale.getISOCountries()).contains(countryCode)) {
            throw new RuntimeException("Invalid country code");
        }

        this.identifier = identifier;
        this.name = name;
        this.countryCode = countryCode;
    }

    /**
     * @brief Getter for the passenger's identifier.
     *
     * @return The unique identifier of the passenger.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @brief Getter for the passenger's name.
     *
     * @return The name of the passenger.
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Getter for the passenger's country code.
     *
     * @return The country code of the passenger.
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @brief Getter for the associated flight.
     *
     * @return The flight associated with the passenger.
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * @brief Joins a flight.
     *
     * This method associates the passenger with a new flight, removing the
     * passenger from the previous flight if applicable.
     *
     * @param flight The flight to join.
     * @throws RuntimeException if the passenger cannot be removed from the
     *                         previous flight or cannot be added to the new flight.
     */
    public void joinFlight(Flight flight) {
        Flight previousFlight = this.flight;
        if (null != previousFlight) {
            if (!previousFlight.removePassenger(this)) {
                throw new RuntimeException("Cannot remove passenger");
            }
        }
        setFlight(flight);
        if (null != flight) {
            if (!flight.addPassenger(this)) {
                throw new RuntimeException("Cannot add passenger");
            }
        }
    }

    /**
     * @brief Sets the associated flight.
     *
     * This method sets the associated flight for the passenger.
     *
     * @param flight The flight to set.
     */
    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    /**
     * @brief Generates a string representation of the passenger.
     *
     * @return A string containing the name, identifier, and country code of
     *         the passenger.
     */
    @Override
    public String toString() {
        return "Passenger " + getName() + " with identifier: " + getIdentifier() + " from " + getCountryCode();
    }
}

