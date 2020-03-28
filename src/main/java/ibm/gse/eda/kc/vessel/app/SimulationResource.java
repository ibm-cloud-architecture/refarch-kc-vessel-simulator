package ibm.gse.eda.kc.vessel.app;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import ibm.gse.eda.kc.vessel.domain.Voyage;
import ibm.gse.eda.kc.vessel.infra.VoyageRepository;

/**
 * SimulationResource
 */
@Path("/vessel")
@ApplicationScoped
public class SimulationResource {

    @Inject
    private VoyageRepository voyageRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Retrieve all supported voyages",description=" Retrieve the voyages supported by the simulation.")
    @APIResponses(
        value = {
            @APIResponse(
                responseCode = "200",
                description = "All support voyages for this simulator",
                content = @Content(mediaType = "application/json",
                schema = @Schema(implementation = Voyage[].class))) 
        })
    public List<Voyage> getSupportedVoyages() {
        return voyageRepository.getVoyages();
    }
}