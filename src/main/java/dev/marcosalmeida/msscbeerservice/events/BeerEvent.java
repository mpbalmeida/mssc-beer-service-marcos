package dev.marcosalmeida.msscbeerservice.events;

import dev.marcosalmeida.msscbeerservice.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent implements Serializable {

    static final long serialVersionUID = 9212824072748683900L;

    private final BeerDto beerDto;
}
