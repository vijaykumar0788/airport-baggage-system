package org.system.airportbaggage.routing.algo;

import java.util.Map;

import org.system.airportbaggage.error.handler.ApplicationException;
import org.system.airportbaggage.routing.algo.types.ConveyorVertex;

/**
 * @author Vijaykumar Patel
 *
 */
public interface BaggageRouteAlog {
	
    public String calculateShortestRoute(String entryPoint, String destPoint, Map<String, ConveyorVertex> conveyorEdgeMap) throws ApplicationException;
}
