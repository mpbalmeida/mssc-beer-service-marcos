package dev.marcosalmeida.msscbeerservice.web.mappers;

import dev.marcosalmeida.msscbeerservice.domain.Beer;
import dev.marcosalmeida.msscbeerservice.web.model.BeerDto;
import dev.marcosalmeida.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BeerMapperTest {

    @Autowired
    BeerMapper beerMapper;

    Beer validBeer;
    BeerDto validBeerDto;

    @BeforeEach
    void setUp() {
        var uuid = UUID.randomUUID();
        validBeer = Beer.builder().id(uuid)
                .beerName("Beer1")
                .beerStyle("PALE_ALE")
                .upc(123456789012L)
                .build();

        validBeerDto = BeerDto.builder().id(uuid)
                .beerName("Beer1")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .upc(123456789012L)
                .build();
    }

    @Test
    void beerToBeerDto() {
        BeerDto beerDto = beerMapper.beerToBeerDto(validBeer);

        assertEquals(validBeerDto, beerDto);
    }

    @Test
    void beerDtoToBeer() {
        Beer beer = beerMapper.beerDtoToBeer(validBeerDto);

        //todo add more validations
        assertEquals(validBeer.getId(), beer.getId());
    }
}