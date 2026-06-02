package com.aritra.healthbridge.kafka;

import com.aritra.healthbridge.event.AppointmentBookedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentEventProducer {

    private final KafkaTemplate<String, AppointmentBookedEvent> kafkaTemplate;
    private static final String TOPIC = "appointment-booked";

    public void publishAppointmentBooked(AppointmentBookedEvent appointmentBookedEvent){
        kafkaTemplate.send(TOPIC,appointmentBookedEvent);
        System.out.println("Published appointment event to Kafka");
    }
}
