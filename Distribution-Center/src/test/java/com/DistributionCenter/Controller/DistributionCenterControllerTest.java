package com.DistributionCenter.Controller;

import com.DistributionCenter.Model.DistributionCenter;
import com.DistributionCenter.Service.DistributionCenterService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.impl.bootstrap.HttpServer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
@WebMvcTest(value = DistributionCenterController.class)
class DistributionCenterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DistributionCenterService distributionCenterService;

    @Test
    void addDistributionCenter() throws Exception{

        DistributionCenter distributionCenter = new DistributionCenter(1L,12344L,"Chennai","regional");
        String inputJson = this.mapToJson(distributionCenter);
        String URI = "/DC";

        Mockito.doNothing().when(distributionCenterService).addDC(Mockito.any(DistributionCenter.class));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(inputJson).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(),response.getStatus());

    }

    @Test
    void getAllDistributionCenter() throws Exception{

        DistributionCenter distributionCenter1 = new DistributionCenter(1L,12344L,"Chennai","regional");
        DistributionCenter distributionCenter2 = new DistributionCenter(2L,12344L,"Bangalore","National");

        List<DistributionCenter> distributionCenterList = new ArrayList<>();
        distributionCenterList.add(distributionCenter1);
        distributionCenterList.add(distributionCenter2);

        Mockito.when(distributionCenterService.getAllDC()).thenReturn(distributionCenterList);

        String URI = "/DCs";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String inputJson = this.mapToJson(distributionCenterList);
        String outputJson = result.getResponse().getContentAsString();
        assertThat(outputJson).isEqualTo(inputJson);


    }

    @Test
    void getDistributionCenter() throws Exception{
        DistributionCenter distributionCenter = new DistributionCenter(1L,12344L,"Chennai","regional");

        String URI = "/DC/1";

        Mockito.when(distributionCenterService.getById(Mockito.anyLong())).thenReturn(distributionCenter);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String inputJson = this.mapToJson(distributionCenter);
        String outputJson = result.getResponse().getContentAsString();
        assertThat(outputJson).isEqualTo(inputJson);
    }

    @Test
    void getDistributionCenterByNumber() throws Exception{
        DistributionCenter distributionCenter = new DistributionCenter(1L,12344L,"Chennai","regional");

        String URI = "/DCByNumber/12344";

        Mockito.when(distributionCenterService.getByNumber(Mockito.anyLong())).thenReturn(distributionCenter);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String inputJson = this.mapToJson(distributionCenter);
        String outputJson = result.getResponse().getContentAsString();
        assertThat(outputJson).isEqualTo(inputJson);
    }


    public String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}