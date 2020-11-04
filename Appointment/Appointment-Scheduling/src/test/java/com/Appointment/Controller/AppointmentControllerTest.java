package com.Appointment.Controller;

import com.Appointment.Model.*;
import com.Appointment.Service.AppointmentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.protocol.types.Field;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AppointmentController.class)
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppointmentService appointmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RestTemplate restTemplate;


    @Test
     void testGetAllAppointments() throws Exception{
        Appointment mockAppointment1 = new Appointment(1L,231,12344,"8.00 - 9.00",16, new Date());
        Appointment mockAppointment2 = new Appointment(2L,231,12344,"8.00 - 9.00",17, new Date());

        List<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(mockAppointment1);
        appointmentList.add(mockAppointment2);

        Mockito.when(appointmentService.getAllAppointments()).thenReturn(appointmentList);

        String URI = "/Appointments";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expectedJson = this.mapToJson(appointmentList);
        String outputInJson = result.getResponse().getContentAsString();
        List<Appointment> outputList = objectMapper.readValue(outputInJson,ArrayList.class);
        assertThat(outputList.size()).isEqualTo(appointmentList.size());
    }

    @Test
     void testAddAppointment() throws Exception{
        Appointment mockAppointment= new Appointment(1L,231L,12344L,"8.00 - 9.00",16L,new Date());
        Apmt apmt = new Apmt();
        List<Long> poNumbers = new ArrayList<>();
        poNumbers.add(14L);
        apmt.setTruckNumber(231L);
        apmt.setDcNumber(12344L);
        apmt.setPoNumbers(poNumbers);
        String json = this.mapToJson(apmt);
        Truck truck = new Truck(1L,231L,"Volvo","Flat-Bed");
        DC dc = new DC(1L,12344L,"Chennai","regional");
        DCSlot dcSlot = new DCSlot(1L,12344L,"8.00 - 9.00",64L);
        PO po = new PO(1L,14L,new Date(),"xxx,yyy,zzz",14L,12L,"upc1",20L);

        String URI = "/Appointment";

        Mockito.when(appointmentService.addAppointment(mockAppointment)).thenReturn(mockAppointment);
        Mockito.when(restTemplate.getForObject("http://Truck/TruckByNumber/231",Truck.class)).thenReturn(truck);
        Mockito.when(restTemplate.getForObject("http://Distribution-Center/DCByNumber/12344",DC.class)).thenReturn(dc);
        Mockito.when(restTemplate.getForObject("http://Distribution-Center/DCSlotByNumber/12344",DCSlot.class)).thenReturn(dcSlot);
        Mockito.when(restTemplate.getForObject("http://Postal-Order/POByNumber/14",PO.class)).thenReturn(po);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(json).
                contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        assertEquals(HttpStatus.OK.value(),response.getStatus());

    }


    public String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    @Test
    void getAppointmentById() throws Exception {
        Appointment mockAppointment= new Appointment(1L,231L,12344L,"8.00 - 9.00",16L,new Date());
        String URI = "/Appointment/1";
        Mockito.when(appointmentService.getAppointmentById(Mockito.anyLong())).thenReturn(mockAppointment);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        String result = response.getContentAsString();
        Appointment appointment = objectMapper.readValue(result,Appointment.class);
        assertThat(appointment).isEqualTo(mockAppointment);
    }
}