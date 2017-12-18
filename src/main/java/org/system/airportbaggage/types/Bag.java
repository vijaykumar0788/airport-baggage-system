
package org.system.airportbaggage.types;

/**
 * @author Vijaykumar Patel
 *
 */

public class Bag {

    private String bagNumber;
    private String entryPoint;
    private String flightId;

    public Bag(String bagNumber,String entryPoint,String flightId) {
		this.bagNumber = bagNumber;
		this.entryPoint = entryPoint;
		this.flightId = flightId;
	}
    
    public String getBagNumber() {
        return bagNumber;
    }

    public void setBagNumber(String bagNumber) {
        this.bagNumber = bagNumber;
    }

    public String getEntryPoint() {
        return entryPoint;
    }

    public void setEntryPoint(String entryPoint) {
        this.entryPoint = entryPoint;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }
}
