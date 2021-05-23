package dev.marcosalmeida.msscbeerservice.events;

import dev.marcosalmeida.msscbeerservice.web.model.BeerDto;

public class NewInventoryEvent extends BeerEvent {

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
