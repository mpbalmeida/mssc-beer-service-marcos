package dev.marcosalmeida.msscbeerservice.services;

import dev.marcosalmeida.msscbeerservice.web.model.BeerDto;
import dev.marcosalmeida.msscbeerservice.web.model.BeerPagedList;
import dev.marcosalmeida.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerDto getById(UUID id, Boolean showInventoryOnHand);

    BeerDto createBeer(BeerDto beer);

    BeerDto updateBeer(UUID id, BeerDto beer);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyleEnum, PageRequest pageRequest, Boolean showInventoryOnHand);

    BeerDto getByUpc(String upc);
}
