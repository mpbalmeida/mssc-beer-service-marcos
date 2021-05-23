package dev.marcosalmeida.msscbeerservice.events;

import dev.marcosalmeida.msscbeerservice.web.model.BeerDto;

public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
