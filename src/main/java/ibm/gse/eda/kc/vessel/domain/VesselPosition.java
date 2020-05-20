package ibm.gse.eda.kc.vessel.domain;

import java.util.Date;

import org.gavaghan.geodesy.GlobalCoordinates;

/**
 * VesselPosition makrs the longitude, lattitude, but instant speed and bearing
 */
public class VesselPosition {


    public String vesselID;
    public String status;
    public double speed;
    public double bearing;
    public double longitude;
    public double lattitude;
    public Date timestamp;

    @Override
	public String toString() {
		StringBuffer sb = new StringBuffer("vesselID: ");
		sb.append(getVesselID())
		.append(" at ")
		.append(timestamp)
		.append(" Lat ")
		.append(lattitude)
		.append(" long ")
		.append(longitude)
        .append(" s= ")
        .append(speed);
		return sb.toString();
    }
    
    public String getVesselID() {
        return this.vesselID;
    }

    public void setVesselID(String vesselID) {
        this.vesselID = vesselID;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getBearing() {
        return this.bearing;
    }

    public void setBearing(double bearing) {
        this.bearing = bearing;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLattitude() {
        return this.lattitude;
    }

    public void setLattitude(double lattitude) {
        this.lattitude = lattitude;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public VesselPosition(Vessel vessel, Date startTime, GlobalCoordinates globalCoordinates) {
        this.timestamp = startTime;
        this.vesselID = vessel.getVesselID();
        this.speed = 0;
        this.lattitude = globalCoordinates.getLatitude();
        this.longitude = globalCoordinates.getLongitude();
	}
}