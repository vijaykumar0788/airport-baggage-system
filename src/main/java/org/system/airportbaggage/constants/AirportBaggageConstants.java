/**
 * 
 */
package org.system.airportbaggage.constants;

/**
 * @author Vijaykumar Patel
 *
 */
public interface AirportBaggageConstants {
	
	
	public final static String BAGGAGE_CLAIM ="BaggageClaim";
	public final static String SECTION_HEADER="# Section:";
	public final static String FLIGHT_ARRIVAL ="ARRIVAL";
	
	public final static String SPACE_SEPARATOR = "\\s+";
	public final static String DIRECTION = "-->";
	public final static String COLON = " : ";
	public final static String BREAKLINE= "\\n";
	public final static String SPACE = " ";
	
	
	public final static String RESPONSE_STATUS_FAILURE ="FAILURE";
	public final static String RESPONSE_STATUS_SUCCESS ="SUCCESS";
	
	/*
	 * PARSER RETRIVING CONST.
	 */
	public final static String TEXT_REQUEST_PARSER = "TEXT";
	//Can be used for future enhancement 
	public final static String JSON_REQUEST_PARSER = "JSON";
	
	/*
	 * Error Codes
	 */
	public final static String VERTEX_MISSING_ERRCODE ="VETEX-MISSING-ERR";
	public final static String STRAT_VERTEX_MISSING_ERRTEXT ="Map does not contain the Starting Point with ID : ";
	public final static String END_VERTEX_MISSING_ERRTEXT ="Map does not contain the Destination Point with ID : ";
	
	public final static String TXT_REQ_PARSE_ERRCODE = "TXT-REQ-PARSE-ERR";
	public final static String INPUR_REQ_DATA_ERRTEXT = "Incomplete Request, Kindly refer provided sample request.";
	public final static String CONVEYOR_DATA_ERRTEXT = "Conveyor System data does not exist in request, Kindly refer provided sample request.";
	public final static String CONVEYOR_DATA_FORMATE_ERRTEXT = "Conveyor System data formate Error, Kindly refer provided sample request.";
	public final static String DEPARTURE_DATA_FORMATE_ERRTEXT = "Departures data formate Error. Please refer to readme for the input data format.";
}
