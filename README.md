# airport-baggage-system
Denver International Airport has decided to give an automated baggage system another shot. The hardware and tracking systems from the previous attempt are still in place, they just need a system to route the baggage.  The system will route baggage checked, connecting, and terminating in Denver.

You have been asked to implement a system that will route bags to their flights or the proper baggage claim.  The input describes the airport conveyer system, the departing flights, and the bags to be routed.  The output is the optimal routing to get bags to their destinations.  Bags with a flight id of �ARRIVAL� are terminating in Denver are routed to Baggage Claim.

Input: The input consists of several sections.  The beginning of each section is marked by a line starting: �# Section:�
```
Section 1: A weighted bi-directional graph describing the conveyor system.
Format: <Node 1> <Node 2> <travel_time>

Section 2: Departure list Format:
<flight_id> <flight_gate> <destination> <flight_time>
Section 3: Bag list Format:
<bag_number> <entry_point> <flight_id>
```

Output: The optimized route for each bag
```
<Bag_Number> <point_1> <point_2> [<point_3>, �] : <total_travel_time>
```

# Usage:
1. git clone https://github.com/vijaykumar0788/airport-baggage-system.git
2. cd airport-baggage-system;
3. gradlew clean build
4. java -jar airport-baggage-system-0.0.1-SNAPSHOT.jar
5. import src\main\resources\airport-baggage-routing.postman_collection.json in postman and execute text-baggageRouteProcessing
   text-baggageRouteProcessing will execute retrieveBaggageRoute operation of BaggageController and return the response.
6. It is recommended but not compulsory to follow Step 5, user can choose their own method to test using various other options.

Note : Controller is implemented with BasicAuth, check below properties in application.properties for the details. 
	   security.user.name
	   security.user.password
	      
	   
# Shortest Path Algorithm Reference (Dijkstra's algorithm)
https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm