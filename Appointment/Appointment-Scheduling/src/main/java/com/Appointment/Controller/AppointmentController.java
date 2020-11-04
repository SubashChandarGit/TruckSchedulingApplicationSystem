package com.Appointment.Controller;


import com.Appointment.Exception.AppointmentNotFoundException;
import com.Appointment.Service.AppointmentService;
import com.Appointment.Model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@RestController
public class AppointmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MessageChannel output;

    @GetMapping("/Appointments")
    public List<Appointment> getAllAppointments() {
        LOGGER.info("START :: Appointment-Scheduling :: AppointmentController :: getAllAppointments");
        return appointmentService.getAllAppointments();
    }

    @PostMapping("/Appointment")
    public Appointment addAppointment(@RequestBody Apmt apmt) {
        Appointment appointment = new Appointment();
        LOGGER.info("START :: Appointment-Scheduling :: AppointmentController :: addAppointment");
        Truck truck = restTemplate.getForObject("http://Truck/TruckByNumber/{number}",Truck.class,apmt.getTruckNumber());
        DC dc = restTemplate.getForObject("http://Distribution-Center/DCByNumber/{number}", DC.class, apmt.getDcNumber());
        DCSlot dcSlot = restTemplate.getForObject("http://Distribution-Center/DCSlotByNumber/{number}",DCSlot.class,apmt.getDcNumber());
        for (long i: apmt.getPoNumbers()) {
            PO po = restTemplate.getForObject("http://Postal-Order/POByNumber/{number}",PO.class, i);
                if(po!= null && dcSlot!= null && truck!= null && dc!= null) {
                    appointment.setDcNumber(dc.getDcNumber());
                    appointment.setDcSlot(dcSlot.getTimeSlot());
                    appointment.setTruckNumber(truck.getNumber());
                    appointment.setPoNumber(po.getPoNumber());
                    Date date = new Date();
                    appointment.setDateOfAppointment(date);
                    appointmentService.addAppointment(appointment);
                    Message<Appointment> message = MessageBuilder.withPayload(appointment).build();
                    output.send(message);
                    return appointment;
                }
        }
        return appointment;
    }

    @GetMapping("/Appointment/{id}")
    public Appointment getAppointmentById(@PathVariable long id) throws AppointmentNotFoundException {
        LOGGER.info("START :: Appointment-Scheduling :: AppointmentController :: getAppointmentById");
        return appointmentService.getAppointmentById(id);
    }

    @PutMapping("/Appointment/{id}")
    public void updateAppointment(@PathVariable long id, @RequestBody Appointment appointment) throws AppointmentNotFoundException {
        LOGGER.info("START :: Appointment-Scheduling :: AppointmentController :: updateAppointment");
        appointmentService.getAppointmentById(id);
        appointmentService.addAppointment(appointment);
    }

    @DeleteMapping("/Appointment/{id}")
    public void deleteAppointment(@PathVariable long id) throws AppointmentNotFoundException {
        LOGGER.info("START :: Appointment-Scheduling :: AppointmentController :: deleteAppointment");
        appointmentService.deleteAppointment(id);
    }
}
