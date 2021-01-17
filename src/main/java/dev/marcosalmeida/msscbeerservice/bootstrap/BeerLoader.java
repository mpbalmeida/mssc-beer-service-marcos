package dev.marcosalmeida.msscbeerservice.bootstrap;

import dev.marcosalmeida.msscbeerservice.domain.Beer;
import dev.marcosalmeida.msscbeerservice.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;

@RequiredArgsConstructor
// Removing bootstrap to give place to data.sql in order to have fixed ids for http test
//@Component
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";

    private final BeerRepository beerRepository;

    @Override
    public void run(String... args) throws Exception {
//        loadBeerObjects();
        loadBeerPerformanceObjects();
    }

    private void loadBeerPerformanceObjects() {
        if (beerRepository.count() == 3) {
            for (int i = 0; i < 100_000_000; i++) {
                beerRepository.save(Beer.builder()
                        .beerName("Mango Bobs" + i)
                        .beerStyle("IPA")
                        .quantityToBrew(200)
                        .minOnHand(12)
                        .upc(Long.valueOf(BEER_1_UPC + i).toString())
                        .price(new BigDecimal("12.95"))
                        .build());
            }
        }
    }

    private void loadBeerObjects() {
        if (beerRepository.count() == 0) {
            beerRepository.save(Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_1_UPC)
                    .price(new BigDecimal("12.95"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(100)
                    .minOnHand(20)
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal("11.95"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("No Hammers On The Bar")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(100)
                    .minOnHand(20)
                    .upc(BEER_3_UPC)
                    .price(new BigDecimal("11.95"))
                    .build());
        }
    }
}
