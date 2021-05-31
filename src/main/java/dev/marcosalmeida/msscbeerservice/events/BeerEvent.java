package dev.marcosalmeida.msscbeerservice.events;

import dev.marcosalmeida.msscbeerservice.web.model.BeerDto;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BeerEvent implements Serializable {

    static final long serialVersionUID = 9212824072748683900L;

    private BeerDto beerDto;
}
