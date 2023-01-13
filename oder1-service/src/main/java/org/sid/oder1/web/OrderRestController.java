package org.sid.oder1.web;

import org.sid.oder1.entities.Order;
import org.sid.oder1.repository.OrderRepository;
import org.sid.oder1.repository.ProductItemRepository;
import org.sid.oder1.service.CustomerRestClient;
import org.sid.oder1.service.ProductRestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class OrderRestController {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private CustomerRestClient customerRestClient;
    @Autowired
    private ProductRestClient productRestClient;


    @GetMapping("/fullOrder/{id}")
    public Order order(@PathVariable Long id){
        Order order=orderRepository.findById(id).get();
        order.setCustomer(customerRestClient.findCustomerById(order.getCustomerId()));
        order.getProductItems().forEach(pi->{
            pi.setProduct(productRestClient.findProductById(pi.getProductId()));
        });
        return order;
    }
}
