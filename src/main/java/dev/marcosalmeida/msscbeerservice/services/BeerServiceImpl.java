package dev.marcosalmeida.msscbeerservice.services;

import dev.marcosalmeida.msscbeerservice.domain.Beer;
import dev.marcosalmeida.msscbeerservice.repository.BeerRepository;
import dev.marcosalmeida.msscbeerservice.web.controller.exceptions.NotFoundException;
import dev.marcosalmeida.msscbeerservice.web.mappers.BeerMapper;
import dev.marcosalmeida.msscbeerservice.web.model.BeerDto;
import dev.marcosalmeida.msscbeerservice.web.model.BeerPagedList;
import dev.marcosalmeida.msscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Cacheable(cacheNames = "beerCache", key = "#id", condition = "#showInventoryOnHand == false ")
    @Override
    public BeerDto getById(UUID id, Boolean showInventoryOnHand) {
        return beerRepository.findById(id)
                .map(beer -> {
                    if (showInventoryOnHand) {
                        return beerMapper.beerToBeerDtoWithInventory(beer);
                    }
                    return beerMapper.beerToBeerDto(beer);
                })
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

    @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false")
    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyleEnum, PageRequest pageRequest, Boolean showInventoryOnHand) {
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
                .map(beer -> {
                    if (showInventoryOnHand) {
                        return beerMapper.beerToBeerDtoWithInventory(beer);
                    }
                    return beerMapper.beerToBeerDto(beer);
                })
                .collect(Collectors.toList()),
                PageRequest
                        .of(beerPage.getPageable().getPageNumber(),
                                beerPage.getPageable().getPageSize()),
                beerPage.getTotalElements());
    }

    @Cacheable(cacheNames = "beerUpcCache", key = "#upc")
    @Override
    public BeerDto getByUpc(String upc) {
        return beerRepository.findBeerByUpc(upc).map(beerMapper::beerToBeerDto).orElseThrow(NotFoundException::new);
    }
}
