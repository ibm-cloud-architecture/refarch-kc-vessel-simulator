package ibm.gse.eda.kc.vessel.domain;

/**
 * Simulate the course of a vessel
 */

public class VesselSimulatorService {
    public static String STARTED = "SimulationStarted";

	public String startSimulation(SimulationControl simulationControl) {
		boolean valid = validateControlParameters(simulationControl);
		if (valid) {
			
		}
		return STARTED;
	}

	private boolean validateControlParameters(SimulationControl simulationControl) {
		return false;
	}


    
}