package com.Vendor.Controller;

import com.Vendor.Model.Vendor;
import com.Vendor.Service.VendorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
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
@WebMvcTest(value = VendorController.class)
class VendorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VendorService vendorService;

    @Test
    void addVendor() throws Exception {
        Vendor vendor = new Vendor(1L,"Vendor","vendor@gmail.com",9876543210L,"abc,def,ghi");
        String inputJson = this.mapToJson(vendor);
        String URI = "/Vendor";

        Mockito.doNothing().when(vendorService).addVendor(Mockito.any(Vendor.class));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).content(inputJson).contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(),response.getStatus());
    }

    @Test
    void getAllVendors() throws Exception {
        Vendor vendor1 = new Vendor(1L,"Vendor1","vendor1@gmail.com",9876543210L,"abc,def,ghi");
        Vendor vendor2 = new Vendor(1L,"Vendor2","vendor2@gmail.com",9876501234L,"xxx,yyy,zzz");
        List<Vendor> vendorList = new ArrayList<>();
        vendorList.add(vendor1);
        vendorList.add(vendor2);

        String URI = "/Vendors";

        Mockito.when(vendorService.getAllVendor()).thenReturn(vendorList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String inputJson = this.mapToJson(vendorList);
        String outputJson = result.getResponse().getContentAsString();
        assertThat(outputJson).isEqualTo(inputJson);
    }

    @Test
    void getVendorById() throws Exception {

        Vendor vendor = new Vendor(1L,"Vendor","vendor@gmail.com",9876543210L,"abc,def,ghi");
        String inputJson = this.mapToJson(vendor);
        String URI = "/Vendor/1";

        Mockito.when(vendorService.getVendorById(Mockito.anyLong())).thenReturn(vendor);

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