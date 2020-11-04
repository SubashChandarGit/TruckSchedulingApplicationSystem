package com.Truck.Controller;

import com.Truck.Model.Truck;
import com.Truck.Service.TruckService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TruckController.class)
class TruckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TruckService truckService;

    @Test
    void addTruck() throws Exception{
        Truck truck = new Truck(1L,231L,"Volvo","Flat-Bed");
        String inputJson = this.mapToJson(truck);
        String URI = "/Truck";

        Mockito.doNothing().when(truckService).addTruck(Mockito.any(Truck.class));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(inputJson).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(),response.getStatus());

    }

    @Test
    void getAllTrucks() throws Exception {
        Truck truck1 = new Truck(1L,231L,"Volvo","Flat-Bed");
        Truck truck2= new Truck(2L,221L,"Scania","Straight");

        List<Truck> truckList = new ArrayList<>();
        truckList.add(truck1);
        truckList.add(truck2);
        String URI = "/Trucks";

        Mockito.when(truckService.getAllTruck()).thenReturn(truckList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String inputJson = this.mapToJson(truckList);
        String outputJson = result.getResponse().getContentAsString();
        assertThat(outputJson).isEqualTo(inputJson);
    }

    @Test
    void getTruckById() throws Exception {
        Truck truck = new Truck(1L,231L,"Volvo","Flat-Bed");
        String inputJson = this.mapToJson(truck);
        String URI = "/Truck/1";

        Mockito.when(truckService.getTruckById(Mockito.anyLong())).thenReturn(truck);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String outputJson = result.getResponse().getContentAsString();
        assertThat(outputJson).isEqualTo(inputJson);
    }

    @Test
    void getTruckByNumber() throws Exception {
        Truck truck = new Truck(1L,231L,"Volvo","Flat-Bed");
        String inputJson = this.mapToJson(truck);
        String URI = "/TruckByNumber/231";

        Mockito.when(truckService.getTruckByNumber(Mockito.anyLong())).thenReturn(truck);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String outputJson = result.getResponse().getContentAsString();
        assertThat(outputJson).isEqualTo(inputJson);
    }

    public String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}