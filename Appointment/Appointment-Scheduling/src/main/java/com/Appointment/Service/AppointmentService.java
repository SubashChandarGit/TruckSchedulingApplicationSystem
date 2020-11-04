package com.Appointment.Service;

import com.Appointment.Model.Appointment;

import java.util.List;


public interface AppointmentService {
    public Appointment addAppointment(Appointment  appointment);
    public List<Appointment> getAllAppointments();
    public Appointment getAppointmentById(long id);
    public void deleteAppointment(long id);
}
