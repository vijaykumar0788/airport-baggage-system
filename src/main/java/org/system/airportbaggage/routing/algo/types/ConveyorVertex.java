package org.system.airportbaggage.routing.algo.types;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vijaykumar Patel
 *
 */

public class ConveyorVertex implements Comparable<ConveyorVertex>{
    private final String name;
    private int time =Integer.MAX_VALUE; //Will be the total time for the shortest path to this Vertex from the source Vertex specfied in the DijkstraGraphMap
    private ConveyorVertex prevVertext = null;
    private final Map<ConveyorVertex, Integer> neighbours = new HashMap<>(); //The neighbour Vertex and the time to it

    public ConveyorVertex(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setPrevVertext(ConveyorVertex prevVertext) {
        this.prevVertext = prevVertext;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public ConveyorVertex getPrevVertext() {
        return prevVertext;
    }

    public Map<ConveyorVertex, Integer> getNeighbours() {
        return neighbours;
    }

    public int compareTo(ConveyorVertex other)
    {
        if (time == other.time)
            return name.compareTo(other.name);

        return Integer.compare(time, other.time);
    }

    /**
     * Return the shortest path to this Vertex from a source specified in a DijkstraGraphMap after running the dijkstra algorithm
     * @return  The list of the shortest vertex path
     */

    public List<ConveyorVertex> getShortestPathTo()
    {
        List<ConveyorVertex> path = new ArrayList();
        path.add(this);
        ConveyorVertex vertex=this.getPrevVertext();
        while (vertex!=null && !path.contains(vertex)) {
            path.add(vertex);
            vertex=vertex.getPrevVertext();
        }

        Collections.reverse(path);
        return path;
    }

    @Override
    public String toString(){
        return this.name+":"+this.time;
    }
}
