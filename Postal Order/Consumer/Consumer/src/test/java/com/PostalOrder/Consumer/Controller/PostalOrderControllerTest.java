package com.PostalOrder.Consumer.Controller;

import com.PostalOrder.Consumer.Model.PostalOrder;
import com.PostalOrder.Consumer.Repository.PostalOrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@WebMvcTest(value = PostalOrderController.class)
class PostalOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PostalOrderRepository postalOrderRepository;

    @Test
    void getPOById() throws Exception {
        PostalOrder postalOrder = new PostalOrder(1L,14L,new Date(),"xxx,yyy,zzz",14L,12L,"upc1",20L);
        String URI = "/PO/1";
        String inputJson = this.mapToJson(postalOrder);

        Mockito.when(postalOrderRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(postalOrder));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String outputJson = result.getResponse().getContentAsString();
        PostalOrder postalOrderOutput = objectMapper.readValue(outputJson,PostalOrder.class);
        assertThat(postalOrderOutput).isEqualTo(postalOrder);

    }

    @Test
    void getPOByNumber() throws Exception {
        PostalOrder postalOrder = new PostalOrder(1L,14L,new Date(),"xxx,yyy,zzz",14L,12L,"upc1",20L);
        String URI = "/POByNumber/14";
        String inputJson = this.mapToJson(postalOrder);

        Mockito.when(postalOrderRepository.findByPoNumber(Mockito.anyLong())).thenReturn(postalOrder);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String outputJson = result.getResponse().getContentAsString();
        PostalOrder postalOrderOutput = objectMapper.readValue(outputJson,PostalOrder.class);
        assertThat(postalOrderOutput).isEqualTo(postalOrder);
    }

    public String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}