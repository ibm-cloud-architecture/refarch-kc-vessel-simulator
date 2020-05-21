package ibm.gse.eda.kc.vessel.domain;

/**
 * Control the simulation work.
 * A simulation runs for simulDuration in seconds, during this time the boat
 * has to move all the route as defined by the given route
 */
public class SimulationControl {

    protected String vesselID;
    protected int routeID;
    protected int simulDuration; // in seconds
    protected String type;  // not yet
    protected int hourStep  = 4;



    public SimulationControl(){}
    
    public SimulationControl(final String vesselID) {
        setVesselID(vesselID);
    }

	public void setVesselID(final String vesselID) {
        this.vesselID = vesselID;
    }

    /**
     * @return the vesselID
     */
    public String getVesselID() {
        return vesselID;
    }

    public void setSimulDuration(final int simulDuration) {
        this.simulDuration = simulDuration;
    }

    public void setRouteID(final int route) {
        this.routeID = route;
    }
    
    /**
     * @return the route
     */
    public int getRouteID() {
        return routeID;
    }

    public int getHourStep() {
        return this.hourStep;
    }

    public void setHourStep(int hourStep) {
		this.hourStep = hourStep;
	}

    public int getSimulDuration() {
        return simulDuration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    };

}
