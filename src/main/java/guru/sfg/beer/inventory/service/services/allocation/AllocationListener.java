package guru.sfg.beer.inventory.service.services.allocation;

import com.comon.brewery.model.events.AllocateOrderRequest;
import com.comon.brewery.model.events.AllocateOrderResult;
import com.comon.brewery.model.events.BeerOrderDto;
import guru.sfg.beer.inventory.service.config.JmsConfigConvert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AllocationListener {
    private final JmsTemplate jmsTemplate;
    private final AllocationService allocationService;
    AllocateOrderResult allocateOrderResult;

    @JmsListener(destination = JmsConfigConvert.ALLOCATE_ORDER_QUEUE)
    public void listen(AllocateOrderRequest allocateOrderRequest)
    {
        Boolean allocate_order = allocationService.allocate_order(allocateOrderRequest);
        AllocateOrderResult.AllocateOrderResultBuilder builder = AllocateOrderResult.builder();

        try {
            allocateOrderResult = allocate_order ? builder.pendingInventory(false).build() : builder.pendingInventory(true).build();
        }
        catch (Exception e)
        {
            log.error("Getting error for Allocation for order id: {}",allocateOrderRequest.getBeerOrderDto().getId().toString() );
            allocateOrderResult.setAllocationError(true);
        }
        jmsTemplate.convertAndSend(JmsConfigConvert.ALLOCATE_ORDER_RESPONSE_QUEUE, allocateOrderResult);
        }

}
