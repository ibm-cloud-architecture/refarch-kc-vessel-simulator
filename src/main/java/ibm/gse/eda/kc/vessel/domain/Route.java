package ibm.gse.eda.kc.vessel.domain;

import java.util.ArrayList;
import java.util.List;

import org.gavaghan.geodesy.GlobalCoordinates;

public class Route {

	protected int id;
	protected String originPort;
	protected String destPort;
	protected double distance;
	protected List<GlobalCoordinates> legs;

	public Route(int id,String originPort, String destPort) {
		this.id = id;
		this.originPort = originPort;
		this.destPort = destPort;
		legs = new ArrayList<GlobalCoordinates>(2);
	}

	public Route(int id,String originPort, String destPort, List<GlobalCoordinates> legs) {
		this.id = id;
		this.originPort = originPort;
		this.destPort = destPort;
		this.legs = legs;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Route ) {
			Route that = (Route)obj;
			if (that.id == this.id) return true;
			if ((that.originPort.equals(this.originPort)
			 	 && this.destPort.equals(that.destPort)) 
			 || (that.destPort.equals(this.originPort) 
				 && this.destPort.equals(that.originPort)
				 )) {
				return true;
			} 
		} return super.equals(obj);
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("id: ");
		sb.append(getId())
		.append(" from ")
		.append(getOriginPort())
		.append(" to ")
		.append(getDestPort())
		.append(" for ")
		.append(getDistance())
		.append(" miles");
		return sb.toString();
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOriginPort() {
		return this.originPort;
	}

	public void setOriginPort(String originPort) {
		this.originPort = originPort;
	}

	public String getDestPort() {
		return this.destPort;
	}

	public void setDestPort(String destPort) {
		this.destPort = destPort;
	}

	public double getDistance() {
		if (this.distance == 0) {
			this.distance = PositionCalculator.getDistance(legs.get(0),legs.get(1));
		}
		return this.distance;
	}

	public void setDistance(double miles) {
		this.distance = miles;
	}

	public List<GlobalCoordinates> getPositions() {
		return legs;
	}


}
