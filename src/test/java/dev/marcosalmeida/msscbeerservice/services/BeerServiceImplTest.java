package dev.marcosalmeida.msscbeerservice.services;

import dev.marcosalmeida.brewery.model.BeerDto;
import dev.marcosalmeida.msscbeerservice.repository.BeerRepository;
import dev.marcosalmeida.msscbeerservice.test.util.BeerTest;
import dev.marcosalmeida.msscbeerservice.web.controller.exceptions.NotFoundException;
import dev.marcosalmeida.msscbeerservice.web.mappers.BeerMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.openMocks;

class BeerServiceImplTest extends BeerTest {

    @Mock
    BeerRepository beerRepository;

    @Mock
    BeerMapper beerMapper;

    BeerServiceImpl service;

    @BeforeEach
    void setUp() {
        openMocks(this);
        service = new BeerServiceImpl(beerRepository, beerMapper);
    }

    @Test
    void getById() {
        var id = UUID.randomUUID();
        given(beerRepository.findById(any())).willReturn(Optional.of(getValidBeer()));
        given(beerMapper.beerToBeerDtoWithInventory(any())).willReturn(getValidDto());

        BeerDto dto = service.getById(id, true);

        assertEquals(getValidDto(), dto);
        then(beerRepository).should(times(1)).findById(any());
        then(beerMapper).should(times(1)).beerToBeerDtoWithInventory(any());
        then(beerRepository).shouldHaveNoMoreInteractions();
        then(beerMapper).shouldHaveNoMoreInteractions();
    }

    @Test
    void getByIdNotFound() {
        var id = UUID.randomUUID();
        given(beerRepository.findById(any())).willReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.getById(id, true));

        then(beerRepository).should(times(1)).findById(any());
        then(beerRepository).shouldHaveNoMoreInteractions();
        then(beerMapper).shouldHaveNoInteractions();
    }

    @Test
    void createBeer() {
    }

    @Test
    void updateBeer() {
    }
}