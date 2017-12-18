package org.system.airportbaggage.routing.algo.types;

/**
 * @author Vijaykumar Patel
 *
 */

public class ConveyorEdge {
    private final ConveyorVertex source;
    private final ConveyorVertex destination;
    private final int time;

    public ConveyorEdge(ConveyorVertex source, ConveyorVertex destination, int time) {
        this.source = source;
        this.destination = destination;
        this.time = time;
    }

    public ConveyorEdge(String sourceName, String destinationName, Integer time) {
        this.source = new ConveyorVertex(sourceName);
        this.destination = new ConveyorVertex(destinationName);
        this.time = time;
    }

    public ConveyorVertex getSource() {
        return source;
    }

    public ConveyorVertex getDestination() {
        return destination;
    }

    public int getTime() {
        return time;
    }
}
