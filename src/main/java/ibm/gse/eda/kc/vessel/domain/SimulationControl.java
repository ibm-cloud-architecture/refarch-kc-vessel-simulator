package ibm.gse.eda.kc.vessel.domain;

public class SimulationControl {

    protected String vesselIdentifier;
    protected Voyage voyage;
    protected int simulDuration;

    public SimulationControl(){}
    
    public SimulationControl(final String vesselID) {
        setVesselID(vesselID);
    }

	public void setVesselID(final String vesselID) {
        this.vesselIdentifier = vesselID;
    }

    public void setSimulDuration(final int simulDuration) {
        this.simulDuration = simulDuration;
    }

    public void setVoyage(final Voyage voyage) {
        this.voyage = voyage;
	}

}
