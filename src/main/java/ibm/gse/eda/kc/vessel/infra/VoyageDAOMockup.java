package ibm.gse.eda.kc.vessel.infra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.gavaghan.geodesy.GlobalCoordinates;

import ibm.gse.eda.kc.vessel.domain.Voyage;

public class VoyageDAOMockup implements VoyageRepository {

    private HashMap<String,List<Voyage>> voyages = new HashMap<String,List<Voyage>>(4);
    
    public VoyageDAOMockup(){
        this.voyages.put("USOAK", Arrays.asList(
        new Voyage(1,"USOAK","CNSHG", Arrays.asList(new GlobalCoordinates(37.815142,-122.477726),new GlobalCoordinates(31.226444,121.560501))),
        new Voyage(2,"USOAK","JPTYO",Arrays.asList(new GlobalCoordinates(37.815142,-122.477726),new GlobalCoordinates(35.652832,139.839478))),
        new Voyage(3,"USOAK","SGSCT",Arrays.asList(new GlobalCoordinates(37.815142,-122.477726),new GlobalCoordinates(1.29309167,103.989441)))));
        this.voyages.put("USLAX",  Arrays.asList(
            new Voyage(4,"USLAX","JPTYO",Arrays.asList(new GlobalCoordinates(34.052235,-118.243683),new GlobalCoordinates(35.652832,139.839478))),
            new Voyage(5,"USLAX","CNSHG",Arrays.asList(new GlobalCoordinates(34.052235,-118.243683),new GlobalCoordinates(31.226444,121.560501))),
            new Voyage(6,"USLAX","SGSCT",Arrays.asList(new GlobalCoordinates(34.052235,-118.243683),new GlobalCoordinates(1.29309167,103.989441)))));
    }
    @Override
    public Voyage getVoyagePerPorts(String src, String dest) {
        if (src == null ) return null;
        if (dest == null) return null;
        if (voyages.get(src) == null) return getVoyagePerPorts(dest,src);
        boolean found = false;
        Iterator<Voyage> l = voyages.get(src).iterator();
        while (! found && l.hasNext()) {
            Voyage v = l.next();
            if (dest.equals(v.getDestPort())) {
                return v;
            }
        }
        return null;
    }

    @Override
    public List<Voyage> getVoyages() {
        List<Voyage> voyageList = new ArrayList<Voyage>();
        for (String key : voyages.keySet()) {
            voyageList.addAll(voyages.get(key));
        }
        return voyageList;
    }

}
