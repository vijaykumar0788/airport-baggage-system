/**
 * 
 */
package org.system.airportbaggage.facade;

import org.system.airportbaggage.constants.AirportBaggageConstants;

/**
 * @author Vijaykumar Patel
 *
 */
/*
 * TO make this class singleton we can directly use @Service annotation Here it
 * is implemented without any annotation
 * 
 * @Service(value = "baggageRequestParsingFacade")
 */
public class BaggageRequestParsingFacade implements BaggageRequestParsing {

	private static BaggageRequestParsingFacade instance = null;

	private BaggageRequestParsingFacade() {
	}

	public static BaggageRequestParsingFacade getInstance() {
		/*
		 * SingleTOne : 
		 * Only one object will created throughout the application life cycle.
		 */
		if (instance == null) {
			instance = new BaggageRequestParsingFacade();
		}
		return instance;
	}
	
	/*
	 * It will return parser object based on input request type
	 * @see org.system.airportbaggage.facade.BaggageRequestParsing#getParser(java.lang.String)
	 */
	public InputRequestParser getParser(String parserName) {
		InputRequestParser iParser = null;
		if (parserName.equals(AirportBaggageConstants.TEXT_REQUEST_PARSER)) {
			iParser = TextInputRequestParserImpl.getInstance();
		}
		/*
		 * Future Enhancement : If requirement comes for JSON request handling
		 * New JsonInputRequestParserImpl need to be created and it will be
		 * returned if request if JSON format. *
		 */
		return iParser;
	}

}
