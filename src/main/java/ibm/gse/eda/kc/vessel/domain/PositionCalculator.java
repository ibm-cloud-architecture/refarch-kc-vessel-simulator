package ibm.gse.eda.kc.vessel.domain;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

public class PositionCalculator {
    public static double NAUTICALMILE = 1852; // in meters
    public static GeodeticCalculator geoCalc = new GeodeticCalculator();
    public static Ellipsoid earth = Ellipsoid.WGS84;
    
    public static GlobalCoordinates getNewPosition(GlobalCoordinates currentPosition, double bearing, double speed, double duration) {
        double[] endBearing = new double[1];
        double distance = speed * duration * NAUTICALMILE;
        return geoCalc.calculateEndingGlobalCoordinates(earth, currentPosition, bearing, distance, endBearing);
    }

	public static double getDistance(GlobalCoordinates start, GlobalCoordinates end) {
        GeodeticCurve geoCurve = geoCalc.calculateGeodeticCurve(earth, start, end);
        return geoCurve.getEllipsoidalDistance() / NAUTICALMILE;
    } 
}