package org.sid.oder1.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.oder1.enums.Status;
import org.sid.oder1.model.Customer;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private Long customerId;
    private Status status;
    private Date orderDate;
    @OneToMany(mappedBy = "order")
    private List<ProductItem> productItems;
    @Transient
    private Customer customer;
}
