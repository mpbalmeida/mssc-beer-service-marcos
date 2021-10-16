package dev.marcosalmeida.msscbeerservice.services;

import dev.marcosalmeida.brewery.model.BeerDto;
import dev.marcosalmeida.brewery.model.BeerPagedList;
import dev.marcosalmeida.brewery.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerDto getById(UUID id, Boolean showInventoryOnHand);

    BeerDto createBeer(BeerDto beer);

    BeerDto updateBeer(UUID id, BeerDto beer);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyleEnum, PageRequest pageRequest, Boolean showInventoryOnHand);

    BeerDto getByUpc(String upc);
}
