package dev.marcosalmeida.msscbeerservice.web.controller;

import dev.marcosalmeida.msscbeerservice.services.BeerService;
import dev.marcosalmeida.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public BeerDto findById(@PathVariable("id") UUID id) {
        return beerService.getById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity createBeer(@Validated @RequestBody BeerDto beer) {
        BeerDto savedBeer = beerService.createBeer(beer);

        return ResponseEntity.created(URI.create("/api/v1/beer/" + savedBeer.getId())).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateBeer(@PathVariable("id") UUID id, @Validated @RequestBody BeerDto beer) {
        beerService.updateBeer(id, beer);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBeer(@PathVariable("id") UUID id) {
        //TODO Implement services
    }
}
