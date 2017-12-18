package org.system.airportbaggage.routing.algo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.system.airportbaggage.constants.AirportBaggageConstants;
import org.system.airportbaggage.error.handler.ApplicationException;
import org.system.airportbaggage.routing.algo.types.ConveyorVertex;

/**
 * @author Vijaykumar Patel
 *
 */
public class BaggageRouteAlgoImpl implements BaggageRouteAlog{

	    Map<String, BaggageRouteProcessor> visitedMap = new HashMap<>(); // Visited map with the sourceName as the key
	    
	    /*
	     * Calculates Shortest Route
	     */
	    @Override
	    public String calculateShortestRoute(String entryPoint, String destPoint, Map<String, ConveyorVertex> conveyorEdgeMap) throws ApplicationException{
	    	BaggageRouteProcessor baggageRouteMap;
	        if(visitedMap.containsKey(entryPoint)){
	        	baggageRouteMap = visitedMap.get(entryPoint);
	        }else {
	        	baggageRouteMap = new BaggageRouteProcessor(conveyorEdgeMap);
	        	baggageRouteMap.dijkstra(entryPoint);
	            visitedMap.put(entryPoint,baggageRouteMap);
	        }

	        List<ConveyorVertex> shortestPath= baggageRouteMap.getShortestRoute(destPoint);
	        return generatePathLine(shortestPath);
	    }

	    /*
	     * Generates final calculated shortest route for each bag
	     */
	    private String generatePathLine(List<ConveyorVertex> path){
	        StringBuffer line = new StringBuffer();

	        for(org.system.airportbaggage.routing.algo.types.ConveyorVertex vertex:path){
	            line.append(vertex.getName()).append(AirportBaggageConstants.SPACE);
	        }
	        line.append(": ").append(path.get(path.size()-1).getTime());
	        return line.toString();
	    }

	    
}
