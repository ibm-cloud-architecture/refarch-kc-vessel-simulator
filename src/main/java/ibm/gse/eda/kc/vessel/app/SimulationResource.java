package ibm.gse.eda.kc.vessel.app;

import java.util.Collection;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.tags.Tags;

import ibm.gse.eda.kc.vessel.domain.Route;
import ibm.gse.eda.kc.vessel.domain.SimulationControl;
import ibm.gse.eda.kc.vessel.domain.Vessel;
import ibm.gse.eda.kc.vessel.domain.VesselPosition;
import ibm.gse.eda.kc.vessel.domain.VesselSimulatorService;
import ibm.gse.eda.kc.vessel.infra.RouteRepository;
import ibm.gse.eda.kc.vessel.infra.VesselRepository;

/**
 * SimulationResource
 */
@Path("/vessel")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Tags(value = @Tag(name = "vessel", description = "All the vessel methods"))
public class SimulationResource {
   
    @Inject
    private RouteRepository routeRepository;
    @Inject
    private VesselRepository vesselRepository;
    @Inject
    private VesselSimulatorService vesselSimulatorService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieve all vessels",description=" Retrieve the vessels in our inventor")
    @APIResponses(
        value = {
            @APIResponse(
                responseCode = "200",
                description = "All supported vessels",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Vessel[].class))) 
     })
     @Timed(name = "vesselsProcessingTime",
     tags = {"method=getVessels"},
     absolute = true,
     description = "Time needed to get the list of vessels")
     @Counted(name = "vesselAccessCount",
       absolute = true,
       description = "Number of times the list of vessel method is requested")
    public Collection<Vessel> getVessels() {
        return vesselRepository.getVessels();
    }

    @GET
    @Path("/routes")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieve all supported routes",description=" Retrieve the vessel routes supported by the simulation.")
    @APIResponses(
        value = {
            @APIResponse(
                responseCode = "200",
                description = "All supported routes a vessel can follow for this simulator",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Route[].class))) 
     })
     @Timed(name = "routesProcessingTime",
     tags = {"method=getSupportedRoutes"},
     absolute = true,
     description = "Time needed to process the simulator call")
     @Counted(name = "routesAccessCount",
       absolute = true,
       description = "Number of times the list of routes method is requested")
    public List<Route> getSupportedRoutes() {
        return routeRepository.getRoutes();
    }

    @POST
    @Path("simulation/start")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Start vessel movement simulation",description="Using a simulation control document to define what to simulate")
    @APIResponses(value = {
        @APIResponse(
            responseCode = "200",
            description = "All different positions a vessel can follow for this simulator",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = VesselPosition[].class))) 
    })
    @Timed(name = "simulatorProcessingTime", tags = {"method=startSimulation"},
        absolute = true,
        description = "Time needed to process the simulator call")
    @Counted(name = "simulationAccessCount",
        absolute = true,
        description = "Number of times the start simulator method is requested")
    public List<VesselPosition> startSimulation(SimulationControl control) {
        return vesselSimulatorService.startSimulation(control);
    }
}