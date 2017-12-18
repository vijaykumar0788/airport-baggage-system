
package org.system.airportbaggage.types;

import java.util.List;
import java.util.Map;

import org.system.airportbaggage.routing.algo.types.ConveyorEdge;


/**
 * @author Vijaykumar Patel
 *
 */

public class BaggageRouteRequest{

    
	private List<ConveyorEdge> conveyorEdge;
	private Map<String,String> departuresMap;
	private List<Bag> bagList;
	
    public BaggageRouteRequest() {
	}
    
	public BaggageRouteRequest(List<ConveyorEdge> converyorEdges, Map<String,String> departuresMap, List<Bag> bagList) {
		this.conveyorEdge = converyorEdges;
		this.departuresMap = departuresMap;
		this.bagList = bagList;
	}

	public List<ConveyorEdge> getConveyorEdge() {
		return conveyorEdge;
	}

	public void setConveyorEdge(List<ConveyorEdge> conveyorEdge) {
		this.conveyorEdge = conveyorEdge;
	}

	public Map<String, String> getDeparturesMap() {
		return departuresMap;
	}

	public void setDeparturesMap(Map<String, String> departuresMap) {
		this.departuresMap = departuresMap;
	}

	public List<Bag> getBagList() {
		return bagList;
	}

	public void setBagList(List<Bag> bagList) {
		this.bagList = bagList;
	}	

}
