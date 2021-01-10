package dev.marcosalmeida.msscbeerservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {

    @Null
    private UUID id;

    @NotBlank
    private String beerName;

    @NotNull
    private BeerStyleEnum beerStyle;

    @NotNull
    @Positive
    private Long upc;
    
    @NotNull
    @Positive
    private BigDecimal price;

    private Integer quantityOnHand;

    @Null
    private Integer version;

    @Null
    private Instant createdDate;

    @Null
    private Instant lastModifiedDate;
}
