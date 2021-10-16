package dev.marcosalmeida.msscbeerservice.web.mappers;

import dev.marcosalmeida.brewery.model.BeerDto;
import dev.marcosalmeida.msscbeerservice.domain.Beer;
import dev.marcosalmeida.msscbeerservice.test.util.BeerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BeerMapperTest extends BeerTest {

    @Autowired
    BeerMapper beerMapper;

    Beer validBeer;
    BeerDto validBeerDto;

    @BeforeEach
    void setUp() {
        var uuid = UUID.randomUUID();
        validBeer = getValidBeer();
        validBeer.setId(uuid);

        validBeerDto = getValidDto();
        validBeerDto.setId(uuid);
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