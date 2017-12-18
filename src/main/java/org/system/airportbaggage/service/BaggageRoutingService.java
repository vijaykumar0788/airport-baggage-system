/**
 * 
 */
package org.system.airportbaggage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.system.airportbaggage.constants.AirportBaggageConstants;
import org.system.airportbaggage.error.handler.ApplicationException;
import org.system.airportbaggage.routing.algo.BaggageRouteAlgoImpl;
import org.system.airportbaggage.routing.algo.types.ConveyorEdge;
import org.system.airportbaggage.routing.algo.types.ConveyorVertex;
import org.system.airportbaggage.types.Bag;
import org.system.airportbaggage.types.BaggageRouteRequest;

/**
 * @author Vijaykumar Patel
 */

/*
 * SingleTone : Achieved through Spring annotation.
 */
@Service(value = "baggageRoutingService")
public class BaggageRoutingService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaggageRoutingService.class);

	/*
	 * Calculate shortest route for each BAG. 
	 */
	public String calculateRoute(BaggageRouteRequest baggageRouteRequest) throws ApplicationException{

		StringBuilder response = new StringBuilder();
		Map<String, String> departureDetails = baggageRouteRequest.getDeparturesMap();
		List<ConveyorEdge> conveyorEdges = baggageRouteRequest.getConveyorEdge();
		Map<String, ConveyorVertex> conveyorEdgeMap = createBaggageRouteMap(conveyorEdges);

		BaggageRouteAlgoImpl routeProcessor = new BaggageRouteAlgoImpl();

		for (Bag bag : baggageRouteRequest.getBagList()) {

			String bagNumber = bag.getBagNumber();
			String flightId = bag.getFlightId();

			String entryPoint = bag.getEntryPoint();
			String destPoint = null;

			if (flightId.equals(AirportBaggageConstants.FLIGHT_ARRIVAL)) {
				destPoint = AirportBaggageConstants.BAGGAGE_CLAIM;
			} else {
				destPoint = departureDetails.get(flightId);
			}
			String pathLine = routeProcessor.calculateShortestRoute(entryPoint, destPoint, conveyorEdgeMap);
			response.append(bagNumber);
			response.append(AirportBaggageConstants.COLON);
			response.append(pathLine);
			response.append(System.lineSeparator());
			LOGGER.info(bagNumber + AirportBaggageConstants.COLON + pathLine);
		}

		return response.toString();
	}

	/*
	 *  Populated all the vertices from the edges
	 */
	private static Map<String, ConveyorVertex> createBaggageRouteMap(List<ConveyorEdge> conveyorEdges) {

		final Map<String, ConveyorVertex> conveyorEdgeMap = new HashMap<>(conveyorEdges.size());

		for (ConveyorEdge e : conveyorEdges) {

			if (!conveyorEdgeMap.containsKey(e.getSource().getName()))
				conveyorEdgeMap.put(e.getSource().getName(), new ConveyorVertex(e.getSource().getName()));

			if (!conveyorEdgeMap.containsKey(e.getDestination().getName()))
				conveyorEdgeMap.put(e.getDestination().getName(), new ConveyorVertex(e.getDestination().getName()));
		}

		for (ConveyorEdge e : conveyorEdges) {
			conveyorEdgeMap.get(e.getSource().getName()).getNeighbours()
					.put(conveyorEdgeMap.get(e.getDestination().getName()), e.getTime());
		}

		return conveyorEdgeMap;
	}
}
