package dev.marcosalmeida.msscbeerservice.test.util;

import dev.marcosalmeida.msscbeerservice.bootstrap.BeerLoader;
import dev.marcosalmeida.msscbeerservice.domain.Beer;
import dev.marcosalmeida.msscbeerservice.web.model.BeerDto;
import dev.marcosalmeida.msscbeerservice.web.model.BeerStyleEnum;

import java.math.BigDecimal;

public class BeerTest {

    protected BeerDto getValidDto() {
        return BeerDto.builder()
                .beerName("Nigh cat")
                .beerStyle(BeerStyleEnum.IPA)
                .price(BigDecimal.valueOf(21.99))
                .upc(BeerLoader.BEER_1_UPC)
                .build();
    }

    protected Beer getValidBeer() {
        return Beer.builder()
                .beerName("Nigh cat")
                .beerStyle("IPA")
                .price(BigDecimal.valueOf(21.99))
                .upc(BeerLoader.BEER_1_UPC)
                .build();
    }
}
