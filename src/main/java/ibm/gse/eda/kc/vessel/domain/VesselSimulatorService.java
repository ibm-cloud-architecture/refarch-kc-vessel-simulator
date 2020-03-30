package ibm.gse.eda.kc.vessel.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.gavaghan.geodesy.GlobalCoordinates;

import ibm.gse.eda.kc.vessel.infra.RouteRepository;
import ibm.gse.eda.kc.vessel.infra.VesselRepository;

/**
 * Simulate the course of a vessel
 */
@ApplicationScoped
public class VesselSimulatorService {

	private Random rand = new Random(); 

	@Inject
	VesselRepository vesselRepository;

	@Inject
	RouteRepository routeRepository;

	public VesselSimulatorService() {
		// needed for bean injection !
	}

	public VesselSimulatorService(VesselRepository vesselRepository, RouteRepository routeRepository) {
		this.routeRepository = routeRepository;
		this.vesselRepository = vesselRepository;
	}

	public List<VesselPosition> startSimulation(SimulationControl simulationControl) {
		boolean valid = validateControlParameters(simulationControl);
		ArrayList<VesselPosition> positions = new ArrayList<VesselPosition>();
		if (valid) {
			Vessel vessel = vesselRepository.getVessel(simulationControl.getVesselID());
			Route route = routeRepository.getRouteById(simulationControl.getRouteID());
			positions.addAll(generateAllPositions(vessel,route,simulationControl.getHourStep()));
		}
		return positions;
	}

	/**
	 * Move the boat at speed for n hours in direction bearing and add each position to the list
	 * until the boat is close enough to the target destination
	 * @param vessel
	 * @param route
	 * @return
	 */
	private Collection<? extends VesselPosition> generateAllPositions(
		Vessel vessel, 
		Route route,
		int hourStep) {

		double speed = vessel.getSpeed();
		ArrayList<VesselPosition> positions = new ArrayList<VesselPosition>();
		VesselPosition newPosition = getStartingPosition(vessel,route);
		positions.add(newPosition);
		GlobalCoordinates currentCoordinates = new GlobalCoordinates(newPosition.getLattitude(), newPosition.getLongitude());
		while (! assessIfArrived(currentCoordinates,route.getPositions().get(1))) {
			double bearing = PositionCalculator.getBearing(currentCoordinates, route.getPositions().get(1));
			double direction = (rand.nextInt() > 0.5 ? 1 : -1);
			speed = vessel.getSpeed() * (1 + direction * rand.nextDouble()*.2);
			currentCoordinates = PositionCalculator.getNewPosition(currentCoordinates, bearing, speed, hourStep);
			Date newTime = addTime(newPosition.getTimestamp(),hourStep);
			newPosition = new VesselPosition(vessel,newTime,currentCoordinates);
			newPosition.setSpeed(speed);
			newPosition.setBearing(bearing);
			System.out.println(newPosition.toString());
			positions.add(newPosition);
		}

		return positions;
	}

	public boolean assessIfArrived(GlobalCoordinates newPosition,GlobalCoordinates destination) {
		return (PositionCalculator.getDistance(newPosition, destination) <= 10);
	}

	private Date addTime(Date inDate,int hours){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(inDate);
		calendar.add(Calendar.HOUR_OF_DAY, hours);
		return calendar.getTime();
	}
	
	public VesselPosition getStartingPosition(String vesselID, int routeID) {
		Vessel vessel = vesselRepository.getVessel(vesselID);
		Route route = routeRepository.getRouteById(routeID);
		Date startTime = new Date();
		return new VesselPosition(vessel,startTime,route.getPositions().get(0));
	}

	public VesselPosition getStartingPosition(Vessel vessel,Route route) {
		Date startTime = new Date();
		return new VesselPosition(vessel,startTime,route.getPositions().get(0));
	}

	private boolean validateControlParameters(SimulationControl simulationControl) {
		if (simulationControl == null) return false;
		if (simulationControl.getVesselID() == null) return false;
		if (simulationControl.getRouteID() <= 0 ) return false;
		return true;
	}


    
}