package ibm.gse.eda.kc.vessel.infra;

import java.util.Collection;
import java.util.HashMap;

import javax.enterprise.context.ApplicationScoped;

import ibm.gse.eda.kc.vessel.domain.Vessel;

@ApplicationScoped
public class VesselRepository {
    protected HashMap<String,Vessel> vessels = new HashMap<String,Vessel>();

    public VesselRepository(){
        Vessel jc = new Vessel("Jiminy Cricket",18);
        vessels.put(jc.getVesselID(),jc);
        Vessel v2 = new Vessel("Bullit",20);
        vessels.put(v2.getVesselID(),v2);
        Vessel v3 = new Vessel("White Spirit",20);
        vessels.put(v3.getVesselID(),v3);
    }

	public Vessel getVessel(String vesselID) {
		return vessels.get(vesselID);
    }
    
    public Collection<Vessel> getVessels() {
        return vessels.values();
    }

}
