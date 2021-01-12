package dev.marcosalmeida.msscbeerservice.services;

import dev.marcosalmeida.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getById(UUID id);

    BeerDto createBeer(BeerDto beer);

    BeerDto updateBeer(UUID id, BeerDto beer);
}
