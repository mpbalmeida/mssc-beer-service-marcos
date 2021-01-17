package dev.marcosalmeida.msscbeerservice.services;

import dev.marcosalmeida.msscbeerservice.domain.Beer;
import dev.marcosalmeida.msscbeerservice.repository.BeerRepository;
import dev.marcosalmeida.msscbeerservice.web.controller.exceptions.NotFoundException;
import dev.marcosalmeida.msscbeerservice.web.mappers.BeerMapper;
import dev.marcosalmeida.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Transactional
    @Override
    public BeerDto getById(UUID id) {
        return beerRepository.findById(id)
                .map(beerMapper::beerToBeerDto)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public BeerDto createBeer(BeerDto beer) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beer)));
    }

    @Override
    public BeerDto updateBeer(UUID id, BeerDto beerDto) {
        Beer beer = beerRepository.findById(id).orElseThrow(NotFoundException::new);
        beerMapper.updateBeer(beerDto, beer);

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }
}
