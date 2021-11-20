package guru.sfg.beer.inventory.service.bootstrap;

import guru.sfg.beer.inventory.service.domain.BeerInventory;
import guru.sfg.beer.inventory.service.repositories.BeerInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by jt on 2019-06-07.
 */
@Slf4j
@RequiredArgsConstructor
//@Component
public class BeerInventoryBootstrap implements CommandLineRunner {
    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
    public static final UUID BEER_1_UUID = UUID.fromString("3c2cf2b9-3cfc-4982-87d1-d5a75ded9b8e");
    public static final UUID BEER_2_UUID = UUID.fromString("4afa8213-4759-4e82-97b5-41273ba492b5");
    public static final UUID BEER_3_UUID = UUID.fromString("4e05b67b-a932-4cd9-9f6b-6ade24595ced");

    private final BeerInventoryRepository beerInventoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if(beerInventoryRepository.count() == 0){
            loadInitialInv();
        }
    }

    private void loadInitialInv() {
        beerInventoryRepository.save(BeerInventory
                .builder()
                .beerId(BEER_1_UUID)
                .upc(BEER_1_UPC)
                .quantityOnHand(50)
                .build());

        beerInventoryRepository.save(BeerInventory
                .builder()
                .beerId(BEER_2_UUID)
                .upc(BEER_2_UPC)
                .quantityOnHand(50)
                .build());

        beerInventoryRepository.saveAndFlush(BeerInventory
                .builder()
                .beerId(BEER_3_UUID)
                .upc(BEER_3_UPC)
                .quantityOnHand(50)
                .build());

        log.debug("Loaded Inventory. Record count: " + beerInventoryRepository.count());
    }
}
