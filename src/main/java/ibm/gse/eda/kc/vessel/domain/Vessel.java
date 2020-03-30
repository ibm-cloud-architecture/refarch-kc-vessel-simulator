package ibm.gse.eda.kc.vessel.domain;

/**
 * Vessel
 */
public class Vessel {

    protected String vessedID;
    protected double speed;

    public Vessel(String id) {
        this.vessedID = id;
        this.speed = 25;
    }
    
    public Vessel(String id, double s) {
        this.vessedID = id;
        this.speed = s;
	}


    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

	public String getVesselID() {
		return vessedID;
	};


    
}