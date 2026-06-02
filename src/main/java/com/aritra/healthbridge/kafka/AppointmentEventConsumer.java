package com.aritra.healthbridge.kafka;

import com.aritra.healthbridge.event.AppointmentBookedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AppointmentEventConsumer {

    @KafkaListener(topics = "appointment-booked", groupId="healthbridge-group")
    public void handleAppointmentBooked(AppointmentBookedEvent event){
        System.out.println("=================================================");
        System.out.println("CONSUMER received event from Kafka:");
        System.out.println("Sending confirmation to patient: " + event.patientName());
        System.out.println("Appointment with Dr. " + event.doctorName());
        System.out.println("Scheduled at: " + event.appointmentDateTime());
        System.out.println("=================================================");
    }
}
