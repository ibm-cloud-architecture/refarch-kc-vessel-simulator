package ut;

import org.gavaghan.geodesy.GlobalCoordinates;
import org.junit.Assert;
import org.junit.Test;

import ibm.gse.eda.kc.vessel.domain.PositionCalculator;

/**
 * GeodesicLineTest
 */
public class GeodesicLineTest {

    @Test
    public void vesselMoveAtSpeed() {
        // boat under golden gate bridge
     GlobalCoordinates currentPosition = new GlobalCoordinates( 37.815142,-122.477726);
     // set the direction and distance
     double startBearing = 275;
     double speed = 6; // in knots
     double duration = 4;  // in hours
     
     GlobalCoordinates dest = PositionCalculator.getNewPosition(currentPosition, startBearing, speed, duration);
     Assert.assertTrue(dest.getLatitude() > currentPosition.getLatitude() );
     Assert.assertTrue(dest.getLongitude() < currentPosition.getLongitude() );
    }
}