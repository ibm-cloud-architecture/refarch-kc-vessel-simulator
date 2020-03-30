package ut;

import java.util.List;

import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Test;

import ibm.gse.eda.kc.vessel.domain.Route;
import ibm.gse.eda.kc.vessel.infra.RouteRepository;


public class RouteTest {
    protected static Gson parser = new Gson();
    protected static RouteRepository routeRepository = new RouteRepository();

    @Test
    public void shouldGetARoute(){
        Route v1 = routeRepository.getRoutePerPorts("USOAK", "CNSHG");
        System.out.println(parser.toJson(v1));
        Assert.assertNotNull(v1);
    }
    @Test
    public void reverseCallShouldGetSameRoute(){
        Route v1 = routeRepository.getRoutePerPorts("USOAK", "CNSHG");
        Route v2 = routeRepository.getRoutePerPorts( "CNSHG", "USOAK");
        Assert.assertNotNull(v2);
        Assert.assertEquals(v1, v2);
    }

    @Test
    public void shouldHavePositions(){
        Route v1 = routeRepository.getRoutePerPorts("USOAK", "CNSHG");
        Assert.assertTrue(v1.getPositions().size() > 0);
    }

    @Test
    public void shouldGetARouteGivenItsID() {
        Route v1 = routeRepository.getRouteById(1);
        Assert.assertTrue(v1.getPositions().size() > 0);
        System.out.println(v1.toString());
    }

    @Test
    public void shouldGetNoRouteWithInvalidID() {
        Route v1 = routeRepository.getRouteById(0);
        Assert.assertNull(v1);
        v1 = routeRepository.getRouteById(10000);
        Assert.assertNull(v1);
    }

    @Test
    public void shouldHaveALongDistance(){
        Route v1 = routeRepository.getRoutePerPorts("USOAK", "CNSHG");
        Assert.assertTrue(v1.getDistance() > 500);
    }

    @Test
    public void testGetAllRoutes(){
        List<Route> routes = routeRepository.getRoutes();
        Assert.assertNotNull(routes);
        Assert.assertTrue(routes.size() > 2);
        for (Route r : routes) {
            System.out.println(r.toString());
        }
    }
}