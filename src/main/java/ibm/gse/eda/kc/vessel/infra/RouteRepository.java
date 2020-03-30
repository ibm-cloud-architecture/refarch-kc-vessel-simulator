package ibm.gse.eda.kc.vessel.infra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.gavaghan.geodesy.GlobalCoordinates;

import ibm.gse.eda.kc.vessel.domain.Route;

/**
 * Keep the inventory of routes from two connected harbors. The key is one of the harbor of the connection.
 * The relation is bidirectional, so the map includes only half of the connections.
 */
@ApplicationScoped
public class RouteRepository {

    private HashMap<String,List<Route>> routes = new HashMap<String,List<Route>>(4);
    
    public RouteRepository(){
        this.routes.put("USOAK", Arrays.asList(
        new Route(1,"USOAK","CNSHG", Arrays.asList(new GlobalCoordinates(37.815142,-122.477726),new GlobalCoordinates(31.226444,121.560501))),
        new Route(2,"USOAK","JPTYO",Arrays.asList(new GlobalCoordinates(37.815142,-122.477726),new GlobalCoordinates(35.652832,139.839478))),
        new Route(3,"USOAK","SGSCT",Arrays.asList(new GlobalCoordinates(37.815142,-122.477726),new GlobalCoordinates(1.29309167,103.989441)))));
        this.routes.put("USLAX",  Arrays.asList(
            new Route(4,"USLAX","JPTYO",Arrays.asList(
                        new GlobalCoordinates(34.052235,-118.243683),
                        new GlobalCoordinates(35.652832,139.839478))),
            new Route(5,"USLAX","CNSHG",Arrays.asList(
                        new GlobalCoordinates(34.052235,-118.243683),
                        new GlobalCoordinates(31.226444,121.560501))),
            new Route(6,"USLAX","SGSCT",Arrays.asList(
                        new GlobalCoordinates(34.052235,-118.243683),
                        new GlobalCoordinates(1.29309167,103.989441)))));
        this.routes.put("TEST",  Arrays.asList(
                new Route(99,"TEST","DEST",Arrays.asList(
                        new GlobalCoordinates(37.815142,-122.477726),
                        new GlobalCoordinates(37.849000, -122.980806)))));
             
        }
    
    /**
     * Return the routes connecting ports
     * @param src
     * @param dest
     * @return
     */
    public Route getRoutePerPorts(String src, String dest) {
        if (src == null ) return null;
        if (dest == null) return null;
        if (routes.get(src) == null) return getRoutePerPorts(dest,src);
        boolean found = false;
        Iterator<Route> l = routes.get(src).iterator();
        while (! found && l.hasNext()) {
            Route v = l.next();
            if (dest.equals(v.getDestPort())) {
                return v;
            }
        }
        return null;
    }

    
    public List<Route> getRoutes() {
        List<Route> routeList = new ArrayList<Route>();
        for (String key : routes.keySet()) {
            routeList.addAll(routes.get(key));
        }
        return routeList;
    }

    /**
     * Search the routes and return the route matching the given id
     * @param routeID
     * @return can be null if no route found
     */
    public Route getRouteById(int routeID){
        if (routeID < 0 ) return null;
        for (String key : routes.keySet()) {
            for (Route r : routes.get(key)) {
                if (r.getId() == routeID) {
                    return r;
                }
            }
        }
        return null;
    }

}
