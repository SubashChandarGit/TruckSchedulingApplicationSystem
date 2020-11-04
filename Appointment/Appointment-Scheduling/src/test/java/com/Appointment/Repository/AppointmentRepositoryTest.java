package com.Appointment.Repository;

import com.Appointment.Model.Appointment;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class AppointmentRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Test
    public void saveAppointment() {
        Appointment appointment = new Appointment();
        appointment.setTruckNumber(231L);
        appointment.setDcNumber(12344L);
        appointment.setDcSlot("6.00 - 7.00");
        appointment.setPoNumber(15L);
        appointment.setDateOfAppointment(new Date());
        Appointment saveInDb = testEntityManager.persist(appointment);
        Appointment getFromDb = appointmentRepository.findById(saveInDb.getId()).get();
        assertEquals(getFromDb,saveInDb);
    }

    @Test
    public void getAllAppointments() {
        Appointment appointment1 = new Appointment();
        appointment1.setTruckNumber(231L);
        appointment1.setDcNumber(12344L);
        appointment1.setDcSlot("6.00 - 7.00");
        appointment1.setPoNumber(15L);
        appointment1.setDateOfAppointment(new Date());

        Appointment appointment2 = new Appointment();
        appointment2.setTruckNumber(221L);
        appointment2.setDcNumber(12345L);
        appointment2.setDcSlot("7.00 - 8.00");
        appointment2.setPoNumber(16L);
        appointment2.setDateOfAppointment(new Date());

        testEntityManager.persist(appointment1);
        testEntityManager.persist(appointment2);

        List<Appointment> appointmentList = (List<Appointment>) appointmentRepository.findAll();
        assertEquals(appointmentList.size(),2);
    }

    @Test
    public void getAppointmentById() {
        Appointment appointment = new Appointment();
        appointment.setTruckNumber(231L);
        appointment.setDcNumber(12344L);
        appointment.setDcSlot("6.00 - 7.00");
        appointment.setPoNumber(15L);
        appointment.setDateOfAppointment(new Date());
        Appointment saveInDb = testEntityManager.persist(appointment);
        Appointment getFromDb = appointmentRepository.findById(saveInDb.getId()).get();
        assertEquals(getFromDb,saveInDb);
    }

    @Test
    public void updateAppointment() {
        Appointment appointment = new Appointment();
        appointment.setTruckNumber(231L);
        appointment.setDcNumber(12344L);
        appointment.setDcSlot("6.00 - 7.00");
        appointment.setPoNumber(15L);
        appointment.setDateOfAppointment(new Date());
        testEntityManager.persist(appointment);
        Appointment getFromDb = appointmentRepository.findById(appointment.getId()).get();
        getFromDb.setDcSlot("7.00 - 8.00");
        testEntityManager.persist(getFromDb);
        assertEquals(getFromDb.getDcSlot(),"7.00 - 8.00");
    }

    @Test
    public void deleteAppointment() {
        Appointment appointment1 = new Appointment();
        appointment1.setTruckNumber(231L);
        appointment1.setDcNumber(12344L);
        appointment1.setDcSlot("6.00 - 7.00");
        appointment1.setPoNumber(15L);
        appointment1.setDateOfAppointment(new Date());

        Appointment appointment2 = new Appointment();
        appointment2.setTruckNumber(221L);
        appointment2.setDcNumber(12345L);
        appointment2.setDcSlot("7.00 - 8.00");
        appointment2.setPoNumber(16L);
        appointment2.setDateOfAppointment(new Date());

        Appointment persist = testEntityManager.persist(appointment1);
        testEntityManager.persist(appointment2);
        testEntityManager.remove(persist);
        List<Appointment> appointmentList = (List<Appointment>) appointmentRepository.findAll();
        assertEquals(appointmentList.size(),1);

    }
}