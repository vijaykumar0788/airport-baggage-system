package org.system.airportbaggage.routing.algo;

import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.system.airportbaggage.constants.AirportBaggageConstants;
import org.system.airportbaggage.error.handler.ApplicationException;
import org.system.airportbaggage.routing.algo.types.ConveyorVertex;

public class BaggageRouteProcessor {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaggageRouteProcessor.class);
	
    // mapping of vertex names to Vertex objects, prepared from set of ConveyerEdges
    private final Map<String, ConveyorVertex> conveyorEdgeMap;
    public BaggageRouteProcessor(Map<String, ConveyorVertex> conveyorEdgeMap) {
    	this.conveyorEdgeMap = conveyorEdgeMap;    	
    }

    /**
     * This will run dijkstra algorithm using a given source node.
     * @param startName is source node for the path
     */
    public void dijkstra(String startName) throws ApplicationException{
        if (!conveyorEdgeMap.containsKey(startName)) {
            throw new ApplicationException(AirportBaggageConstants.VERTEX_MISSING_ERRCODE,
            		AirportBaggageConstants.STRAT_VERTEX_MISSING_ERRTEXT+startName);
        }
        final ConveyorVertex source = conveyorEdgeMap.get(startName);
        NavigableSet<ConveyorVertex> queue = new TreeSet<>();

        // populate vertices to the queue
        for (ConveyorVertex v : conveyorEdgeMap.values()) {
            v.setPrevVertext( v == source ? source : null);
            v.setTime(v == source ? 0 : Integer.MAX_VALUE);
            queue.add(v);
        }

        dijkstra(queue);
    }

    /**
     * Gives shortest path of Node for a specific destination Node
     * @param endName the destination Node name
     * @return the shortest path as a List<Vertex>
     */

    public List<ConveyorVertex> getShortestRoute(String endName) throws ApplicationException{
        if (!conveyorEdgeMap.containsKey(endName)) {
            throw new ApplicationException(AirportBaggageConstants.VERTEX_MISSING_ERRCODE,
            		AirportBaggageConstants.END_VERTEX_MISSING_ERRTEXT+endName);
        }

        return conveyorEdgeMap.get(endName).getShortestPathTo();
    }

    /*
     *  Implementation of dijkstra's algorithm using a binary heap.
     */
    private void dijkstra(final NavigableSet<ConveyorVertex> que) {
        ConveyorVertex source, neighbour;
        while (!que.isEmpty()) {

            source = que.pollFirst(); // vertex with shortest distance 
            if (source.getTime() == Integer.MAX_VALUE) break; // ignore unreachable

            //calculate distance to each neighbor
            for (Map.Entry<ConveyorVertex, Integer> a : source.getNeighbours().entrySet()) {
                neighbour = a.getKey();

                final int alternateTime = source.getTime() + a.getValue();
                if (alternateTime < neighbour.getTime()) { 
                    que.remove(neighbour);
                    neighbour.setTime(alternateTime);
                    neighbour.setPrevVertext(source);
                    que.add(neighbour);
                }
            }
        }
    }
    
}
