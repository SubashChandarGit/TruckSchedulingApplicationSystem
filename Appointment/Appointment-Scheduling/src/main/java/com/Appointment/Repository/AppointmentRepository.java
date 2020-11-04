package com.Appointment.Repository;

import com.Appointment.Model.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {

}
