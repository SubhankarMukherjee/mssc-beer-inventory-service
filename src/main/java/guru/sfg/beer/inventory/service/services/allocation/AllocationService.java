package guru.sfg.beer.inventory.service.services.allocation;

import com.comon.brewery.model.events.AllocateOrderRequest;

public interface AllocationService {
    Boolean allocate_order(AllocateOrderRequest allocateOrderRequest);
}
