package ibm.gse.eda.kc.vessel.infra.kafka;

import ibm.gse.eda.kc.vessel.domain.events.VesselCourseEvent;

public interface EventEmitter {

    public void emit(VesselCourseEvent event) throws Exception;
    public void safeClose();

}
