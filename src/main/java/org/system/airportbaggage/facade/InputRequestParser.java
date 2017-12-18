package org.system.airportbaggage.facade;

import org.system.airportbaggage.error.handler.ApplicationException;
import org.system.airportbaggage.types.BaggageRouteRequest;

/**
 * @author Vijaykumar Patel
 *
 */
public interface InputRequestParser {
	public BaggageRouteRequest parse(String requestData) throws ApplicationException;
}
