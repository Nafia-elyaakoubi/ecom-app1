package org.sid.orderservice;

import org.sid.orderservice.entities.Order;
import org.sid.orderservice.entities.ProductItem;
import org.sid.orderservice.enums.Status;
import org.sid.orderservice.model.Customer;
import org.sid.orderservice.model.Product;
import org.sid.orderservice.repository.OrderRepository;
import org.sid.orderservice.repository.ProductItemRepository;
import org.sid.orderservice.service.CustomerRestClient;
import org.sid.orderservice.service.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(OrderRepository orderRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient,
                            ProductRestClient productRestClient){
        return args -> {
            Collection<Product> products=productRestClient.allProducts().getContent();
            //create customer
            Long customerId = 1L;
            Customer customer= customerRestClient.findCustomerById(customerId);
            if (customer == null) throw new RuntimeException("customer not found");
            Order order  = new Order();
            order.setOrderDate(new Date());
            order.setCustomerId(customerId);
            order.setStatus(Status.PENDING);
            Order savedOrder =  orderRepository.save(order);
            products.forEach(product -> {
                ProductItem productItem = new ProductItem();
                productItem.setOrder(savedOrder);
                productItem.setProductId(product.getId());
                productItem.setQuantity( 1 +new Random().nextInt(10));
                productItem.setPrice(product.getPrice());
                productItem.setDiscount(Math.random());
                productItemRepository.save(productItem);
            });


        };
    }

}
