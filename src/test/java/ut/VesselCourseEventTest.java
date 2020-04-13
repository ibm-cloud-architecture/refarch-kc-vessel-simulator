package ut;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.io.JsonEncoder;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.Test;

import ibm.gse.eda.kc.vessel.domain.events.VesselCourseEvent;

public class VesselCourseEventTest {

    @Test
    public void testUsingGeneratedAvroClass() {
        VesselCourseEvent jc = new VesselCourseEvent("jimminy_cricket"
                , "AtSea", 20, 260, "124.10", "27.23",1233459);
        String inEvent = "{'vessel_id':'jimminy_cricket'}";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DatumWriter<VesselCourseEvent> writer = new SpecificDatumWriter<>(VesselCourseEvent.class);
        byte[] data = new byte[0];
        try {
            JsonEncoder jsonEncoder = EncoderFactory.get().jsonEncoder(VesselCourseEvent.getClassSchema(), out, true);
            writer.write(jc, jsonEncoder);
            jsonEncoder.flush();
            data = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(new String(data));
    }
}