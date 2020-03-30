package ibm.gse.eda.kc.vessel.infra.kafka;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

import ibm.gse.eda.kc.vessel.domain.events.VesselCourseEvent;

@ApplicationScoped
public class VesselCourseEventProducer implements EventEmitter {
	 private static final Logger logger = Logger.getLogger(VesselCourseEventProducer.class.getName());

		@Inject
		private KafkaConfiguration kafkaConfiguration;
		
		private final Jsonb jsonb = JsonbBuilder.create();
		private KafkaProducer<String, String> kafkaProducer;

		public VesselCourseEventProducer() {
			init();
		}

		protected void init() {
			final Properties properties = getKafkaConfiguration().getProducerProperties("order-event-producer");
			kafkaProducer = new KafkaProducer<String, String>(properties);

		}

		public KafkaConfiguration getKafkaConfiguration() {
			// to bypass some strange NPE, as inject does not seem to work
			if (kafkaConfiguration == null) {
				kafkaConfiguration = new KafkaConfiguration();
			}
			return kafkaConfiguration;
		}

		@Override
		@Retry(retryOn = TimeoutException.class, maxRetries = 4, maxDuration = 10000, delay = 200, jitter = 100, abortOn = InterruptedException.class)
		@Timeout(KafkaConfiguration.PRODUCER_TIMEOUT_SECS * 1000)
		public void emit(final VesselCourseEvent inEvent) throws Exception {
			final String value = jsonb.toJson(inEvent);
			logger.info("Send " + value);
			final String key = inEvent.getVesselId().toString();
			final ProducerRecord<String, String> record = new ProducerRecord<>(
					getKafkaConfiguration().getTopicName(), key, value);

			final Future<RecordMetadata> send = kafkaProducer.send(record, new Callback() {

				@Override
				public void onCompletion(final RecordMetadata metadata, final Exception exception) {
					if (exception != null) {
						exception.printStackTrace();
					} else {
						System.out.println("The offset of the record just sent is: " + metadata.offset());

					}
				}
			});
			try {
				send.get(KafkaConfiguration.PRODUCER_TIMEOUT_SECS, TimeUnit.SECONDS);
			} catch (final ExecutionException e) {
				e.printStackTrace();
			}
	    }

		@Override
		public void safeClose() {
			kafkaProducer.close();
		}
}
