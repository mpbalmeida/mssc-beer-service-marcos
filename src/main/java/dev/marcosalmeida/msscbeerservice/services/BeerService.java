package dev.marcosalmeida.msscbeerservice.services;

import dev.marcosalmeida.msscbeerservice.web.model.BeerDto;
import dev.marcosalmeida.msscbeerservice.web.model.BeerPagedList;
import dev.marcosalmeida.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerDto getById(UUID id);

    BeerDto createBeer(BeerDto beer);

    BeerDto updateBeer(UUID id, BeerDto beer);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyleEnum, PageRequest pageRequest);
}
