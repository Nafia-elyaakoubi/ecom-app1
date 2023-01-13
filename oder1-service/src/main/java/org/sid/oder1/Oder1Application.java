package org.sid.oder1;

import org.sid.oder1.entities.Order;
import org.sid.oder1.entities.ProductItem;
import org.sid.oder1.enums.Status;
import org.sid.oder1.model.Customer;
import org.sid.oder1.model.Product;
import org.sid.oder1.repository.OrderRepository;
import org.sid.oder1.repository.ProductItemRepository;
import org.sid.oder1.service.CustomerRestClient;
import org.sid.oder1.service.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
public class Oder1Application {

    public static void main(String[] args) {
        SpringApplication.run(Oder1Application.class, args);
    }

    @Bean
    CommandLineRunner start(OrderRepository orderRepository,
                            ProductItemRepository productItemRepository,
                            CustomerRestClient customerRestClient,
                            ProductRestClient  productRestClient){
        return args -> {
            Collection<Product > products=productRestClient.allProducts().getContent();
            //create customer
            Long customerId = 1L;
            Customer customer= customerRestClient.findCustomerById(customerId);
            if (customer == null) throw new RuntimeException("customer not found");
            Order order  = new Order();
            System.out.println("DONE 1");
            order.setOrderDate(new Date());
            System.out.println("DONE 2");
            order.setCustomerId(customerId);
            System.out.println("DONE 3");
            order.setStatus(Status.PENDING);
            System.out.println("DONE 4");
            System.out.println(order.getId());
            System.out.println(order.getOrderDate());
            System.out.println(order.getCustomerId());

            Order savedOrder =  orderRepository.save(order);
            System.out.println("DONE 5");
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
