package dev.marcosalmeida.msscbeerservice.services.brewing;

import dev.marcosalmeida.msscbeerservice.config.JmsConfig;
import dev.marcosalmeida.msscbeerservice.events.BrewBeerEvent;
import dev.marcosalmeida.msscbeerservice.repository.BeerRepository;
import dev.marcosalmeida.msscbeerservice.services.inventory.BeerInventoryService;
import dev.marcosalmeida.msscbeerservice.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000) // every 5 seconds
    public void checkForLowInventory() {
         beerRepository.findAll().forEach(beer -> {
             Integer onHandInventory = beerInventoryService.getOnHandInventory(beer.getId());

             log.debug("Min onhand is {}", beer.getMinOnHand());
             log.debug("Inventory is {}", onHandInventory);

             if (beer.getMinOnHand() >= onHandInventory) {
                 jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
             }
         });
    }
}
