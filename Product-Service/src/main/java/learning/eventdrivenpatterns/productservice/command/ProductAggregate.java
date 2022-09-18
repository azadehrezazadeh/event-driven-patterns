package learning.eventdrivenpatterns.productservice.command;


import com.fasterxml.jackson.databind.util.BeanUtil;
import learning.eventdrivenpatterns.productservice.event.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Aggregate
@Component
public class ProductAggregate {
    @AggregateIdentifier
    private  String id;
    private  String name;
    private  BigDecimal price;

    public ProductAggregate()
    {


    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand)
    {
        //validateion for command

        ProductCreatedEvent productCreatedEvent =new ProductCreatedEvent();
        BeanUtils.copyProperties(createProductCommand,productCreatedEvent);

        AggregateLifecycle.apply(productCreatedEvent);


    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent)
    {
        this.id = productCreatedEvent.getId();
        this.name =productCreatedEvent.getName();
        this.price = productCreatedEvent.getPrice();

    }
}
