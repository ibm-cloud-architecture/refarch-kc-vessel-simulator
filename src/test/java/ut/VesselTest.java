package ut;

import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Test;

import ibm.gse.eda.kc.vessel.domain.Vessel;
import ibm.gse.eda.kc.vessel.infra.VesselRepository;

/**
 * VesselTest
 */
public class VesselTest {
    protected static Gson parser = new Gson();
    protected static VesselRepository vesselRepository = new VesselRepository();

    @Test
    public void shouldGetAVesselGivenItsID(){
        Vessel v = vesselRepository.getVessel("Jiminy Cricket");
        Assert.assertNotNull(v);
        Assert.assertTrue(v.getSpeed() >= 18);
    }
}