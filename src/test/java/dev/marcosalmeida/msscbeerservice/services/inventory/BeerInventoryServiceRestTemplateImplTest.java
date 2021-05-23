package dev.marcosalmeida.msscbeerservice.services.inventory;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    @Test
    void getOnHandInventory() {
//        Integer onHandInventory = beerInventoryService.getOnHandInventory(BeerLoader.BEER_1_UUID);
//
//        System.out.println(onHandInventory);
    }
}