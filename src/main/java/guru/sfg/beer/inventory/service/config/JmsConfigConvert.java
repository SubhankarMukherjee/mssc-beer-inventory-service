package guru.sfg.beer.inventory.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;


@Configuration

public class JmsConfigConvert {

    public static final String BFREWING_REQUEST_QUEUE= "brewing-request";
    public static final String INVENTORY_EVENT_QUEUE= "inventory_queue";
    //public static final String MY_SEND_RCV_QUEUE="send-receive";
    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper)
    {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        converter.setObjectMapper(objectMapper);  // set spring boot managed instance of object mapper
        return converter;
    }


}