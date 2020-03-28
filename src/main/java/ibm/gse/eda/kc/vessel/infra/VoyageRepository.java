package ibm.gse.eda.kc.vessel.infra;

import java.util.List;

import ibm.gse.eda.kc.vessel.domain.Voyage;

public interface VoyageRepository {

    public Voyage getVoyagePerPorts(String src, String dest);

	public List<Voyage> getVoyages();
}