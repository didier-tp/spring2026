package tp.appliSpring;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import io.micrometer.observation.ObservationRegistry;
import io.micrometer.observation.ObservationTextPublisher;
import io.micrometer.observation.aop.ObservedAspect;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ObservabilityHandlerConfig {


        /* // ObservationRegistry
        @Bean
        ObservationRegistry observationRegistry() {
        ObservationRegistry myObservationRegistry =ObservationRegistry.create();
        //myObservationRegistry.observationHandler(new ObservationTextPublisher(System.out::println)); //for basic local display
        return myObservationRegistry;
        } */
        @Bean
        public ObservationHandler<Observation.Context> observationTextPublisher() {
            return new ObservationTextPublisher(log::info); //to display observation in logger
        }

    // To have the @Observed support we need to register this aspect
    // (it's seems already done in spring boot 3 , but not in spring boot 4 , ... )
    @Bean
    ObservedAspect observedAspect(ObservationRegistry observationRegistry) {
    return new ObservedAspect(observationRegistry);
    }

}
