package org.sid.inventoryservice.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullProduct" , types = Product.class)
public interface
ProductProjection {
    public String getName();
    public int getQuantity();
    public Double getPrice();
}
