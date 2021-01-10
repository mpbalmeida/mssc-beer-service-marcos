package dev.marcosalmeida.msscbeerservice.web.controller;

import dev.marcosalmeida.msscbeerservice.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    public ResponseEntity createBeer(@Validated @RequestBody BeerDto beer) {
        //TODO Implement services

        return ResponseEntity.created(URI.create("/api/v1/beer/")).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateBeer(@PathVariable("id") UUID id, @Validated @RequestBody BeerDto beer) {
        
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBeer(@PathVariable("id") UUID id) {
        //TODO Implement services
    }
}
