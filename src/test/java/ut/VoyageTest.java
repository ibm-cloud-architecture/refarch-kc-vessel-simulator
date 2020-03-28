package ut;

import com.google.gson.Gson;

import org.junit.Assert;
import org.junit.Test;

import ibm.gse.eda.kc.vessel.domain.Voyage;
import ibm.gse.eda.kc.vessel.infra.VoyageRepository;
import ibm.gse.eda.kc.vessel.infra.VoyageDAOMockup;


public class VoyageTest {
    protected static Gson parser = new Gson();
    protected static VoyageRepository dao = new VoyageDAOMockup();

    @Test
    public void shouldGetAVoyage(){
        Voyage v1 = dao.getVoyagePerPorts("USOAK", "CNSHG");
        System.out.println(parser.toJson(v1));
        Assert.assertNotNull(v1);
    }
    @Test
    public void shouldGetSameVoyage(){
        Voyage v1 = dao.getVoyagePerPorts("USOAK", "CNSHG");
        Voyage v2 = dao.getVoyagePerPorts( "CNSHG", "USOAK");
        Assert.assertNotNull(v2);
        Assert.assertEquals(v1, v2);
    }

    @Test
    public void shouldHavePositions(){
        Voyage v1 = dao.getVoyagePerPorts("USOAK", "CNSHG");
        Assert.assertTrue(v1.getPositions().size() > 0);
    }

    @Test
    public void shouldHaveALongDistance(){
        Voyage v1 = dao.getVoyagePerPorts("USOAK", "CNSHG");
        Assert.assertTrue(v1.getDistance() > 500);
    }
}