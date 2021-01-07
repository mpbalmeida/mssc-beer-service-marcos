package dev.marcosalmeida.msscbeerservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
    private UUID id;
    private String beerName;
    private BeerStyleEnum beerStyle;
    private Long upc;
    private BigDecimal price;
    private Integer version;
    private Integer quantityOnHand;
    private Instant createdDate;
    private Instant lastModifiedDate;
}
