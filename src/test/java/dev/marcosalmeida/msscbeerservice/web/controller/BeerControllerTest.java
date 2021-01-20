package dev.marcosalmeida.msscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.marcosalmeida.msscbeerservice.services.BeerService;
import dev.marcosalmeida.msscbeerservice.test.util.BeerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({BeerController.class})
class BeerControllerTest extends BeerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    @Test
    void findById() throws Exception {

        given(beerService.getById(any(), any(Boolean.class))).willReturn(getValidDto());

        mockMvc.perform(get("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void createBeer() throws Exception {

        var validDtoWithId = getValidDto();
        validDtoWithId.setId(UUID.randomUUID());

        given(beerService.createBeer(getValidDto())).willReturn(validDtoWithId);

        var body = objectMapper.writeValueAsString(getValidDto());

        mockMvc.perform(post("/api/v1/beer")
                .content(body)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().stringValues("Location", "/api/v1/beer/" + validDtoWithId.getId()))
                .andExpect(jsonPath("$.id").doesNotExist());
    }

    @Test
    void updateBeer() throws Exception {

        var validDto = getValidDto();
        validDto.setId(null);

        var id = UUID.randomUUID();

        given(beerService.updateBeer(id, validDto)).willReturn(getValidDto());

        var body = objectMapper.writeValueAsString(validDto);

        mockMvc.perform(put("/api/v1/beer/{beerId}", id)
                .content(body)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteBeer() throws Exception {
        mockMvc.perform(delete("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


}