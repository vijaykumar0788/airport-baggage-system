/**
 * 
 */
package org.system.airportbaggage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.system.airportbaggage.constants.AirportBaggageConstants;
import org.system.airportbaggage.error.handler.ApplicationException;
import org.system.airportbaggage.facade.BaggageRequestParsing;
import org.system.airportbaggage.facade.BaggageRequestParsingFacade;
import org.system.airportbaggage.facade.InputRequestParser;
import org.system.airportbaggage.service.BaggageRoutingService;
import org.system.airportbaggage.types.BaggageRouteRequest;

/**
 * @author Vijaykumar Patel
 *
 */
@SpringBootApplication
@RestController
@RequestMapping("/baggage")
public class BaggageController {
	
	/*
	 * If singleton is achieved using spring annotation, 
	 * object of that class can be retrieved using @Autowired 
	 *  
	 *	@Autowired
	 *	@Qualifier("baggageRequestParsingFacade")
	 *	BaggageRequestParsingFacade baggageRequestParsingFacade; 
	 */
	
	
	@Autowired
	@Qualifier("baggageRoutingService")
	BaggageRoutingService baggageRoutingService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BaggageController.class);
	
	@RequestMapping(method = { RequestMethod.POST }, value = "/retrieveBaggageRoute", produces = MediaType.ALL_VALUE, consumes = MediaType.ALL_VALUE)
	public String baggageRouteProcessor(@RequestBody String inputRequest){
		
		String reponse = "Failed to calculate shrtest path!";
		BaggageRouteRequest baggageRouteRequest;
		 
		BaggageRequestParsing baggageDataParsingFactory = BaggageRequestParsingFacade.getInstance();
		InputRequestParser iParser = baggageDataParsingFactory.getParser(AirportBaggageConstants.TEXT_REQUEST_PARSER);
		try {
			baggageRouteRequest = iParser.parse(inputRequest);
			reponse = baggageRoutingService.calculateRoute(baggageRouteRequest);
		} catch (ApplicationException e) {
			LOGGER.error(e.getMessage());
			reponse = e.getErrorCode()+" : "+ e.getErrorText();
		}
		
		return reponse;
	}
	
	
	
}
