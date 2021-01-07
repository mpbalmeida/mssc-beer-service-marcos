package dev.marcosalmeida.msscbeerservice.web.controller;

import dev.marcosalmeida.msscbeerservice.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public BeerDto findById(@PathVariable("id") UUID id) {

        //TODO Implement services
        return BeerDto.builder().build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createBeer(@RequestBody BeerDto beer) {
        //TODO Implement services
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBeer(@PathVariable("id") UUID id) {
        //TODO Implement services
    }
}
