package dev.marcosalmeida.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.marcosalmeida.msscbeerservice.web.model.BeerDto;
import dev.marcosalmeida.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createBeer() throws Exception {

        var body = BeerDto.builder()
                .beerName("Nigh cat")
                .beerStyle(BeerStyleEnum.IPA)
                .price(BigDecimal.valueOf(21.99))
                .upc(22L)
                .build();


        mockMvc.perform(post("/api/v1/beer")
                .content(objectMapper.writeValueAsString(body))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void updateBeer() throws Exception {

        var body = BeerDto.builder()
                .beerName("Nigh cat")
                .beerStyle(BeerStyleEnum.IPA)
                .price(BigDecimal.valueOf(21.99))
                .upc(22L)
                .build();


        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID())
                .content(objectMapper.writeValueAsString(body))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteBeer() throws Exception {
        mockMvc.perform(delete("/api/v1/beer/" + UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}