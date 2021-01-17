package dev.marcosalmeida.msscbeerservice.services;

import dev.marcosalmeida.msscbeerservice.domain.Beer;
import dev.marcosalmeida.msscbeerservice.repository.BeerRepository;
import dev.marcosalmeida.msscbeerservice.web.controller.exceptions.NotFoundException;
import dev.marcosalmeida.msscbeerservice.web.mappers.BeerMapper;
import dev.marcosalmeida.msscbeerservice.web.model.BeerDto;
import dev.marcosalmeida.msscbeerservice.web.model.BeerPagedList;
import dev.marcosalmeida.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Transactional
    @Override
    public BeerDto getById(UUID id) {
        return beerRepository.findById(id)
                .map(beerMapper::beerToBeerDtoWithInventory)
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

    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyleEnum, PageRequest pageRequest) {
        Page<Beer> beerPage;

        if (StringUtils.hasLength(beerName) && beerStyleEnum != null) {
            // search for both
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyleEnum.name(), pageRequest);
        } else if (StringUtils.hasLength(beerName)) {
            //search only for beer name
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (beerStyleEnum != null) {
            // search only by beer style
            beerPage = beerRepository.findAllByBeerStyle(beerStyleEnum.name(), pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        return new BeerPagedList(beerPage
                .getContent()
                .stream()
                .map(beerMapper::beerToBeerDtoWithInventory)
                .collect(Collectors.toList()),
                PageRequest
                        .of(beerPage.getPageable().getPageNumber(),
                                beerPage.getPageable().getPageSize()),
                beerPage.getTotalElements());
    }
}
