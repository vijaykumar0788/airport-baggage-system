package org.system.airportbaggage.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.util.StringUtils;
import org.system.airportbaggage.constants.AirportBaggageConstants;
import org.system.airportbaggage.error.handler.ApplicationException;
import org.system.airportbaggage.routing.algo.types.ConveyorEdge;
import org.system.airportbaggage.types.Bag;
import org.system.airportbaggage.types.BaggageRouteRequest;

/**
 * @author Vijaykumar Patel
 *
 */

public class TextInputRequestParserImpl implements InputRequestParser {

	private static TextInputRequestParserImpl instance = null;

	private TextInputRequestParserImpl() {
	}

	/*
	 * SingleTOne : 
	 * Only one object will created throughout the application
	 * life cycle.
	 */
	public static TextInputRequestParserImpl getInstance() {
		if (instance == null) {
			instance = new TextInputRequestParserImpl();
		}
		return instance;
	}

	@Override
	public BaggageRouteRequest parse(String request) throws ApplicationException {

		BaggageRouteRequest baggageRouteRequest = null;
		
		initialValidation(request);
		
		Scanner scanner = new Scanner(request);

		List<ConveyorEdge> converyorEdges = parseInputGraph(scanner);

		// Map with the flightID as a key and the destination gate as the value
		Map<String, String> departuresMap = parseInputDepartures(scanner);

		List<Bag> bagList = parseInputBags(scanner);

		baggageRouteRequest = new BaggageRouteRequest(converyorEdges, departuresMap, bagList);

		return baggageRouteRequest;
	}

	/*
	 * It will validate and parse # Section: Conveyer System from input request
	 */
	private List<ConveyorEdge> parseInputGraph(Scanner scanner) throws ApplicationException {
		
		String graphSection = scanner.nextLine();
		if (!graphSection.startsWith(AirportBaggageConstants.SECTION_HEADER)) {
			throw new ApplicationException(AirportBaggageConstants.TXT_REQ_PARSE_ERRCODE,
					AirportBaggageConstants.CONVEYOR_DATA_ERRTEXT);
		}
		String nextElement = scanner.nextLine();
		List<ConveyorEdge> edges = new ArrayList<>();
		while (!nextElement.startsWith(AirportBaggageConstants.SECTION_HEADER)) {
			String[] parts = nextElement.trim().split(AirportBaggageConstants.SPACE_SEPARATOR);
			if (parts.length >= 3) {

				ConveyorEdge directedEdge = new ConveyorEdge(parts[0], parts[1], Integer.valueOf(parts[2]));
				edges.add(directedEdge);
				ConveyorEdge rConveyorEdge = new ConveyorEdge(parts[1], parts[0], Integer.valueOf(parts[2]));
				edges.add(rConveyorEdge);

			} else {
				throw new ApplicationException(AirportBaggageConstants.TXT_REQ_PARSE_ERRCODE,
						AirportBaggageConstants.CONVEYOR_DATA_FORMATE_ERRTEXT);
			}
			nextElement = scanner.nextLine();
		}
		return edges;
	}

	/*
	 * It will validate and parse # Section: Departures from input request
	 */
	private Map<String, String> parseInputDepartures(Scanner scanner) throws ApplicationException {
		String nextElement = scanner.nextLine();
		Map<String, String> departuresMap = new HashMap<>();
		while (!nextElement.startsWith(AirportBaggageConstants.SECTION_HEADER)) {
			String[] parts = nextElement.trim().split(AirportBaggageConstants.SPACE_SEPARATOR);
			if (parts.length >= 2) {
				departuresMap.put(parts[0], parts[1]);
			} else {
				throw new ApplicationException(AirportBaggageConstants.TXT_REQ_PARSE_ERRCODE,
						AirportBaggageConstants.DEPARTURE_DATA_FORMATE_ERRTEXT);
			}
			nextElement = scanner.nextLine();
		}
		return departuresMap;
	}

	/*
	 * It will parse # Section: Bags from input request
	 */
	private List<Bag> parseInputBags(Scanner scanner) {
		String next;
		List<Bag> bagList = new ArrayList<>();
		do {
			next = scanner.nextLine();
			String[] parts = next.trim().split(AirportBaggageConstants.SPACE_SEPARATOR);
			if (parts.length >= 3) {
				Bag bag = new Bag(parts[0], parts[1], parts[2]);

				bagList.add(bag);
			} else {
				scanner.close();
				break;
			}
		} while (scanner.hasNextLine());
		return bagList;
	}
	
	private void initialValidation(String request) throws ApplicationException{
		if (StringUtils.countOccurrencesOf(request, AirportBaggageConstants.SECTION_HEADER) < 3) {
			throw new ApplicationException(AirportBaggageConstants.TXT_REQ_PARSE_ERRCODE,
					AirportBaggageConstants.INPUR_REQ_DATA_ERRTEXT);
		}
	}
	
}
