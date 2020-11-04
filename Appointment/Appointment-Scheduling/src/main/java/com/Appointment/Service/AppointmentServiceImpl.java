package com.Appointment.Service;

import com.Appointment.Model.Appointment;
import com.Appointment.Repository.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentServiceImpl.class);


    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment addAppointment(Appointment appointment) {
        LOGGER.info("START :: AppointmentScheduling :: AppointmentServiceImpl :: addAppointment ");
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        LOGGER.info("START :: AppointmentScheduling :: AppointmentServiceImpl :: getAllAppointments ");
        return (List<Appointment>) appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointmentById(long id) {
        LOGGER.info("START :: AppointmentScheduling :: AppointmentServiceImpl :: getAppointmentById ");
        Optional<Appointment> appointment =  appointmentRepository.findById(id);
        Appointment appointment1 = null;
        if (appointment.isPresent()) {
            appointment1 =  appointment.get();
        }
        return appointment1;
    }

    @Override
    public void deleteAppointment(long id) {
        LOGGER.info("START :: AppointmentScheduling :: AppointmentServiceImpl :: deleteAppointment ");
        appointmentRepository.deleteById(id);
    }
}
