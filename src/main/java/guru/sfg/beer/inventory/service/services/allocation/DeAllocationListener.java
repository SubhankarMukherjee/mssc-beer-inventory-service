package guru.sfg.beer.inventory.service.services.allocation;

import com.comon.brewery.model.event.DeallocateOrderRequest;
import guru.sfg.beer.inventory.service.config.JmsConfigConvert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class DeAllocationListener {
    private final AllocationService allocationService;

    @JmsListener(destination = JmsConfigConvert.DEALLOCATE_ORDER_QUEUE)
    public void listen(DeallocateOrderRequest deallocateOrderRequest) {

        allocationService.deAllocate_order(deallocateOrderRequest);

    }
}
