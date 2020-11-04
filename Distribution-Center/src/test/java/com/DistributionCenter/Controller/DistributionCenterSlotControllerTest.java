package com.DistributionCenter.Controller;

import com.DistributionCenter.Model.DCSlot;
import com.DistributionCenter.Model.DistributionCenter;
import com.DistributionCenter.Model.DistributionCenterSlot;
import com.DistributionCenter.Service.DistributionCenterSlotService;
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

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DistributionCenterSlotController.class)
class DistributionCenterSlotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DistributionCenterSlotService distributionCenterSlotService;

    @Test
    void addDistributionCenterSlot() throws Exception {
        DCSlot dcSlot = new DCSlot(1L,12344L,"8.00 - 9.00",64L);
        String inputJson = this.mapToJson(dcSlot);
        String URI = "/DCSlot";

        Mockito.doNothing().when(distributionCenterSlotService).addDCSlot(Mockito.any(DCSlot.class));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(inputJson).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(),response.getStatus());

    }

    @Test
    void getAllDistributionCenterSlots() throws Exception {
        DistributionCenterSlot distributionCenterSlot1 =
                new DistributionCenterSlot(1L,new DistributionCenter(1L,12344L,"Chennai","regional"),"8.00 - 9.00",64L);
        DistributionCenterSlot distributionCenterSlot2 =
                new DistributionCenterSlot(2L,new DistributionCenter(2L,12344L,"Bangalore","National"),"9.00 - 10.00",35L);

        List<DistributionCenterSlot> distributionCenterSlotList = new ArrayList<>();
        distributionCenterSlotList.add(distributionCenterSlot1);
        distributionCenterSlotList.add(distributionCenterSlot2);

        String URI = "/DCSlots";

        Mockito.when(distributionCenterSlotService.getAllDCSlots()).thenReturn(distributionCenterSlotList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String inputJson = this.mapToJson(distributionCenterSlotList);
        String outputJson = result.getResponse().getContentAsString();
        assertThat(outputJson).isEqualTo(inputJson);
    }


    @Test
    void getDistributionCenterSlot() throws Exception {
        DistributionCenterSlot distributionCenterSlot =
                new DistributionCenterSlot(1L,new DistributionCenter(1L,12344L,"Chennai","regional"),"8.00 - 9.00",64L);

        String inputJson = this.mapToJson(distributionCenterSlot);

        String URI = "/DCSlot/1";

        Mockito.when(distributionCenterSlotService.getSlotById(Mockito.anyLong())).thenReturn(distributionCenterSlot);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String outputJson = result.getResponse().getContentAsString();
        assertThat(outputJson).isEqualTo(inputJson);
    }

    @Test
    void getDistributionCenterSlotByNumber() throws Exception {
        DCSlot dcSlot = new DCSlot(1L,12344L,"8.00 - 9.00",64L);

        String inputJson = this.mapToJson(dcSlot);

        String URI = "/DCSlotByNumber/12344";

        Mockito.when(distributionCenterSlotService.getSlotByNumber(Mockito.anyLong())).thenReturn(dcSlot);

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