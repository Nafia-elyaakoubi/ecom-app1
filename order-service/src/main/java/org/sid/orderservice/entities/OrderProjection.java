package org.sid.orderservice.entities;

import org.sid.orderservice.enums.Status;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "fullOrder" , types = Order.class)
public interface OrderProjection {
    Long getId();
    Date getOrderDate();
    Long getCustomerId();
    Status getStatus();


}
