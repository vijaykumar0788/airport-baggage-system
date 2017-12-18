///**
// * 
// */
//package org.system.airportbaggage.service;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.util.StringUtils;
//import org.system.airportbaggage.constants.AirportBaggageConstants;
//import org.system.airportbaggage.routing.algo.BaggageRouteProcessor;
//import org.system.airportbaggage.routing.algo.types.ConveyorEdge;
//import org.system.airportbaggage.routing.algo.types.ConveyorVertex;
//import org.system.airportbaggage.types.Bag;
//import org.system.airportbaggage.types.BaggageRouteResponse;
//import org.system.airportbaggage.types.ConveyorSystem;
//import org.system.airportbaggage.types.Departure;
//
///**
// * @author vpatel004c
// *
// */
//@Service(value = "baggageRoutingService")
//public class AirportBaggageService {
//
//	private static final Logger LOGGER = LoggerFactory.getLogger(AirportBaggageService.class);
//	
//	public BaggageRouteResponse calculateRoute(BaggageRouteRequest baggageRouteRequest){
//		
//		Map<String,String> departureDetails = parseDepartures(baggageRouteRequest);
//		List<ConveyorEdge> conveyorEdges = parseConveyorSystem(baggageRouteRequest);
//		Map<String, ConveyorVertex> conveyorEdgeMap = createBaggageRouteMap(conveyorEdges);
//		List<BagRoute> baggageRoute;
//		BaggageRouteProcessor routeProcessor = new BaggageRouteProcessor();
//		
//		 for(Bag bag: baggageRouteRequest.getBags()){
//			 
//             String bagNumber=bag.getBagNumber();
//             String flightId = bag.getFlightId();
//             
//             String entryPoint=bag.getEntryPoint();
//             String destPoint =  null;
//             
//             if(flightId.equals(AirportBaggageConstants.FLIGHT_ARRIVAL)){
//            	 destPoint = AirportBaggageConstants.BAGGAGE_CLAIM;
//             }else{
//            	 destPoint= departureDetails.get(flightId);
//             }
//             String pathLine=routeProcessor.calculateShortestRoute(entryPoint,destPoint,conveyorEdgeMap);
//             
//             LOGGER.info(bagNumber+ ":" +pathLine);
//         }
//		 
//		BaggageRouteResponse baggageRouteResponse = new BaggageRouteResponse();
//		//baggageRouteResponse.setBagRoute(baggageRoute);
//		return baggageRouteResponse;
//	}
//	
//	private static Map<String,String> parseDepartures(BaggageRouteRequest baggageRouteRequest){
//        
//        Map<String,String> departuresMap=new HashMap<>();
//        
//        for (Departure departureDetail : baggageRouteRequest.getDepartures()) {
//        	if(!StringUtils.isEmpty(departureDetail.getFlightId()) && !StringUtils.isEmpty(departureDetail.getFlightGate())){
//        		departuresMap.put(departureDetail.getFlightId(),departureDetail.getFlightGate());
//        	}else{
//        		LOGGER.debug("Invalide Departure Details !!");
//        		//throw new IllegalArgumentException("Illegal arguments or inputs. Please refer to readme for the input data format."); //TODO
//        	}
//		}
//        return departuresMap;
//    }
//	
//	private static List<ConveyorEdge> parseConveyorSystem(BaggageRouteRequest baggageRouteRequest){
//        
//		List<ConveyorEdge> edges=new ArrayList<>();
//		
//        for (ConveyorSystem conveyorSystem : baggageRouteRequest.getConveyorSystem()) {
//        	if(!StringUtils.isEmpty(conveyorSystem.getSourceNode()) 
//        			&& !StringUtils.isEmpty(conveyorSystem.getDestinaionNode())
//        			&& null != conveyorSystem.getTravelTime()){
//        		ConveyorEdge conveyorEdge = new ConveyorEdge(conveyorSystem.getSourceNode(),conveyorSystem.getDestinaionNode(),
//        				conveyorSystem.getTravelTime());
//        		ConveyorEdge revConveyorEdge = new ConveyorEdge(conveyorSystem.getDestinaionNode(),conveyorSystem.getSourceNode(),
//        				conveyorSystem.getTravelTime());
//        		edges.add(conveyorEdge);
//        		edges.add(revConveyorEdge);
//        	}else{
//        		LOGGER.debug("Invalide Departure Details !!");
//        		//throw new IllegalArgumentException("Illegal arguments or inputs. Please refer to readme for the input data format."); //TODO
//        	}
//		}
//        return edges;
//    }
//	
//	private static Map<String, ConveyorVertex> createBaggageRouteMap(List<ConveyorEdge> conveyorEdges) {
//
//		final Map<String, ConveyorVertex> conveyorEdgeMap = new HashMap<>(conveyorEdges.size());
//
//        //Populated all the vertices from the edges
//        for (ConveyorEdge e : conveyorEdges) {
//            
//        	if (!conveyorEdgeMap.containsKey(e.getSource().getName())) 
//        		conveyorEdgeMap.put(e.getSource().getName(), new ConveyorVertex(e.getSource().getName()));
//            
//            if (!conveyorEdgeMap.containsKey(e.getDestination().getName())) 
//            	conveyorEdgeMap.put(e.getDestination().getName(), new ConveyorVertex(e.getDestination().getName()));
//        }
//
//      LOGGER.debug("...........Vertex...........");
//      for (String name: conveyorEdgeMap.keySet()){
//
//          String key =name;
//          ConveyorVertex value = conveyorEdgeMap.get(key);  
//          LOGGER.debug(key + "--> " + value.getName() + " : "+ value.getTime());  
//      }
//      LOGGER.debug("...........Neighbours..........."); 
//        
//        //Set all the neighbours
//        for (ConveyorEdge e : conveyorEdges) {
//        	LOGGER.debug("___"+e.getSource().getName()+" "+e.getDestination().getName() +" "+e.getTime());
//        	conveyorEdgeMap.get(e.getSource().getName()).getNeighbours().put(conveyorEdgeMap.get(e.getDestination().getName()), e.getTime());
//        }
//        
//      LOGGER.debug("-------------------Whole MAP Start------------------------"); 
//      for (String name: conveyorEdgeMap.keySet()){
//
//          String key =name.toString();
//          ConveyorVertex value = conveyorEdgeMap.get(name);  
//          LOGGER.debug(key + "--> " + value.getName() + " : "+ value.getTime());  
//          Map<ConveyorVertex, Integer> neighnours = value.getNeighbours();
//          for (ConveyorVertex vertex: neighnours.keySet()){
//
//              String vertexKey =vertex.getName();
//              ConveyorVertex VertexValue = conveyorEdgeMap.get(vertexKey);  
//              LOGGER.debug("-----"+vertex.getName() + "--> " + vertex.getTime() + " : "+ VertexValue);  
//          }
//            
//      }
//      LOGGER.debug("-------------------Whole MAP END------------------------");
//      return conveyorEdgeMap;
//    }
//	
//	/*private static Map<String, ConveyorVertex> createBaggageRouteMap(List<ConveyorEdge> conveyorEdges) {
//
//		final Map<String, ConveyorVertex> conveyorEdgeMap = new HashMap<>(conveyorEdges.size());
//
//        //Populated all the vertices from the edges
//        for (ConveyorEdge e : conveyorEdges) {
//            
//        	if (!conveyorEdgeMap.containsKey(e.getSource().getName())) 
//        		conveyorEdgeMap.put(e.getSource().getName(), new ConveyorVertex(e.getSource().getName()));
//            
//            if (!conveyorEdgeMap.containsKey(e.getDestination().getName())) 
//            	conveyorEdgeMap.put(e.getDestination().getName(), new ConveyorVertex(e.getDestination().getName()));
//        }
//
//      LOGGER.debug("...........Vertex...........");
//      for (String name: conveyorEdgeMap.keySet()){
//
//          String key =name;
//          ConveyorVertex value = conveyorEdgeMap.get(key);  
//          LOGGER.debug(key + "--> " + value.getName() + " : "+ value.getTime());  
//      }
//      LOGGER.debug("...........Neighbours..........."); 
//        
//        //Set all the neighbours
//        for (ConveyorEdge e : conveyorEdges) {
//        	LOGGER.debug("___"+e.getSource().getName()+" "+e.getDestination().getName() +" "+e.getTime());
//        	conveyorEdgeMap.get(e.getSource().getName()).getNeighbours().put(conveyorEdgeMap.get(e.getDestination().getName()), e.getTime());
//        	
//        	for (String name: conveyorEdgeMap.keySet()){
//
//                String key =name.toString();
//                ConveyorVertex value = conveyorEdgeMap.get(name);  
//                 
//                Map<ConveyorVertex, Integer> neighnours = value.getNeighbours();
//                for (ConveyorVertex vertex: neighnours.keySet()){
//                	//LOGGER.debug("		"+key + "--> " + value.getName() + " : "+ value.getTime()); 
//                    String vertexKey =vertex.getName();
//                    ConveyorVertex VertexValue = conveyorEdgeMap.get(vertexKey); 
//                    LOGGER.debug("			"+vertex.getName() + "--> " + vertex.getTime() + " : "+ VertexValue);  
//                    
//                }
//                  
//            }
//        }
//        
//      LOGGER.debug("-------------------------------------------"); 
//      for (String name: conveyorEdgeMap.keySet()){
//
//          String key =name.toString();
//          ConveyorVertex value = conveyorEdgeMap.get(name);  
//          LOGGER.debug(key + "--> " + value.getName() + " : "+ value.getTime());  
//          Map<ConveyorVertex, Integer> neighnours = value.getNeighbours();
//          for (ConveyorVertex vertex: neighnours.keySet()){
//
//              String vertexKey =vertex.getName();
//              ConveyorVertex VertexValue = conveyorEdgeMap.get(vertexKey);  
//              LOGGER.debug("-----"+vertex.getName() + "--> " + vertex.getTime() + " : "+ VertexValue);  
//          }
//            
//      }
//      LOGGER.debug("-------------------------------------------");
//      return conveyorEdgeMap;
//    }*/
//	
//}
