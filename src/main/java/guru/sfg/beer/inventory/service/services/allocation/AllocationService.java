package guru.sfg.beer.inventory.service.services.allocation;

import com.comon.brewery.model.event.AllocateOrderRequest;
import com.comon.brewery.model.event.DeallocateOrderRequest;

public interface AllocationService {
    Boolean allocate_order(AllocateOrderRequest allocateOrderRequest);
    void deAllocate_order(DeallocateOrderRequest deallocateOrderRequest);
}
