package guru.sfg.beer.inventory.service.services;

import com.comon.brewery.model.events.NewInventoryEvent;
import guru.sfg.beer.inventory.service.config.JmsConfigConvert;
import guru.sfg.beer.inventory.service.domain.BeerInventory;
import guru.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service  //@Component can also happen
public class NewInventoryListener {

        private final BeerInventoryRepository beerInventoryRepository;

        @JmsListener(destination = JmsConfigConvert.INVENTORY_EVENT_QUEUE)
        public void listen(NewInventoryEvent event){

                log.debug("Got Inventory: " + event.toString());

                beerInventoryRepository.save(BeerInventory.builder()
                        .beerId(event.getBeerDto().getId())
                        .upc(event.getBeerDto().getUpc())
                        .quantityOnHand(event.getBeerDto().getQuantityOnHand())
                        .build());
        }
}
