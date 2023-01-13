package org.sid.orderservice.entities;


import lombok.*;
import org.sid.orderservice.enums.Status;
import org.sid.orderservice.model.Customer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Long customerId;
    private Status status;
    private Date orderDate;
    @OneToMany(mappedBy = "order")
    private List<ProductItem> productItems;
    @Transient
    private Customer customer;
}
