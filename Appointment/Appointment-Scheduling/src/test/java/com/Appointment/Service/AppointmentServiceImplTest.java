package com.Appointment.Service;

import com.Appointment.Model.Appointment;
import com.Appointment.Repository.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AppointmentServiceImplTest {

    @Autowired
    private AppointmentService appointmentService;

    @MockBean
    private AppointmentRepository appointmentRepository;

    @Test
    void addAppointment() {
        Appointment mockAppointment= new Appointment(1L,231,12344,"8.00 - 9.00",16,new Date());

        Mockito.when(appointmentRepository.save(mockAppointment)).thenReturn(mockAppointment);

        assertEquals(appointmentService.addAppointment(mockAppointment),mockAppointment);
    }

    @Test
    void getAllAppointments() {
        Appointment mockAppointment1= new Appointment(1L,231,12344,"8.00 - 9.00",16,new Date());
        Appointment mockAppointment2= new Appointment(2L,221,12345,"9.00 - 10.00",18,new Date());

        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(mockAppointment1);
        appointmentList.add(mockAppointment2);

        Mockito.when(appointmentRepository.findAll()).thenReturn(appointmentList);
        assertEquals(appointmentService.getAllAppointments(),appointmentList);

    }

    @Test
    void getAppointmentById() {
        Appointment mockAppointment= new Appointment(1L,231,12344,"8.00 - 9.00",16,new Date());
        Mockito.when(appointmentRepository.findById(1L)).thenReturn(java.util.Optional.of(mockAppointment));
        assertEquals(appointmentService.getAppointmentById(1L),mockAppointment);
    }

    @Test
    void deleteAppointment() {
        Appointment mockAppointment= new Appointment(1L,231,12344,"8.00 - 9.00",16,new Date());
        Mockito.when(appointmentRepository.findById(1L)).thenReturn(java.util.Optional.of(mockAppointment));
        Mockito.when(appointmentRepository.existsById(1L)).thenReturn(false);
        assertEquals(appointmentRepository.existsById(1L),false);
    }
}