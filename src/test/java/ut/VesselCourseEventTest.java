package ut;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.io.JsonEncoder;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import ibm.gse.eda.kc.vessel.domain.events.VesselCourseEvent;

public class VesselCourseEventTest {

    @Test
    public void shouldSerializeBeanToJsonStringUsingAvroEncoder() {
        VesselCourseEvent jc = new VesselCourseEvent("jimminy_cricket"
                , "AtSea", 20, 260, "124.10", "27.23",1233459);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        DatumWriter<VesselCourseEvent> writer = new SpecificDatumWriter<>(VesselCourseEvent.class);
        byte[] data = new byte[0];
        try {
            // the true is for pretty format
            JsonEncoder jsonEncoder = EncoderFactory.get().jsonEncoder(VesselCourseEvent.getClassSchema(), out, false);
            writer.write(jc, jsonEncoder);
            jsonEncoder.flush();
            data = out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String outString = new String(data);
        System.out.println(outString);
        Assertions.assertTrue(outString.contains("AtSea"));
    }

    @Test
    public void shouldHaveBeanFromString(){
        String inBean = "{\"vessel_id\":\"jimminy_cricket\",\"status\":\"AtSea\",\"speed\":20,\"bearing\":260,\"longitude\":\"124.10\",\"lattitude\":\"27.23\",\"timestamp\":1233459}";
        DatumReader<VesselCourseEvent> reader
        = new SpecificDatumReader<>(VesselCourseEvent.class);
       Decoder decoder = null;
       try {
           decoder = DecoderFactory.get().jsonDecoder(
           VesselCourseEvent.getClassSchema(), inBean);
           VesselCourseEvent vce = reader.read(null, decoder);
           Assertions.assertNotNull(vce);
           Assertions.assertTrue(vce.getStatus().toString().equals("AtSea"));
       } catch (IOException e) {
           System.err.println("Deserialization error:" + e.getMessage());
       }
    }

}