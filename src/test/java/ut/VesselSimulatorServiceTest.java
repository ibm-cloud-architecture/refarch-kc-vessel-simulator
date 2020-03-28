package ut;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ibm.gse.eda.kc.vessel.domain.SimulationControl;
import ibm.gse.eda.kc.vessel.domain.VesselSimulatorService;
import ibm.gse.eda.kc.vessel.domain.Voyage;

public class VesselSimulatorServiceTest {

    private static VesselSimulatorService service;
    @BeforeClass
    public static void setup() {
        service = new VesselSimulatorService();
    }

    @Test
    public void shouldStartSimulation(){
        SimulationControl simulationControl = new SimulationControl();
        simulationControl.setVesselID("JimminyCricket");
        simulationControl.setSimulDuration(60);
        Voyage voyage = new Voyage(1,"USOAK","CNCHG");
        simulationControl.setVoyage(voyage);
        String simulID = service.startSimulation(simulationControl);
        Assert.assertNotNull(simulID);
    }
}