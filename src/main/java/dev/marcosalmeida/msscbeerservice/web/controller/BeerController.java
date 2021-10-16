package dev.marcosalmeida.msscbeerservice.web.controller;

import dev.marcosalmeida.brewery.model.BeerDto;
import dev.marcosalmeida.brewery.model.BeerPagedList;
import dev.marcosalmeida.brewery.model.BeerStyleEnum;
import dev.marcosalmeida.msscbeerservice.services.BeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
    @GetMapping
    public BeerPagedList listBeers(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "25") Integer pageSize,
                                   @RequestParam(value = "beerName", required = false) String beerName,
                                   @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
                                   @RequestParam(value = "showInventoryOnHand", required = false, defaultValue = "false") Boolean showInventoryOnHand) {
        return beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public BeerDto findById(@PathVariable("id") UUID id,
                            @RequestParam(value = "showInventoryOnHand", required = false, defaultValue = "false") Boolean showInventoryOnHand) {
        return beerService.getById(id, showInventoryOnHand);
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/upc/{upc}")
    public BeerDto findByUpc(@PathVariable("upc") String upc) {
        return beerService.getByUpc(upc);
    }
}
