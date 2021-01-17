package dev.marcosalmeida.msscbeerservice.web.mappers;

import dev.marcosalmeida.msscbeerservice.domain.Beer;
import dev.marcosalmeida.msscbeerservice.web.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(uses = DateMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
@DecoratedWith(BeerMapperDecorator.class)
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);

    void updateBeer(BeerDto beerWithNewValues, @MappingTarget Beer beerToBeUpdated);

    BeerDto beerToBeerDtoWithInventory(Beer beer);
}
