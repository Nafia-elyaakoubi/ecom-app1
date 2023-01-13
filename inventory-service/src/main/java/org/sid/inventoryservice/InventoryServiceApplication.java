package org.sid.inventoryservice;

import org.sid.inventoryservice.entities.Product;
import org.sid.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration repositoryRestConfiguration){
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Product.class);
            productRepository.saveAll(
                    List.of(
                            Product.builder().name("product1").quantity(5).price(854545.5).build(),
                            Product.builder().name("product2").quantity(15).price(8457.5).build(),
                            Product.builder().name("product3").quantity(45).price(84585.5).build(),
                            Product.builder().name("product4").quantity(5).price(854545.5).build(),
                            Product.builder().name("product5").quantity(15).price(8457.5).build(),
                            Product.builder().name("product6").quantity(45).price(84585.5).build(),
                            Product.builder().name("product7").quantity(5).price(854545.5).build(),
                            Product.builder().name("product8").quantity(15).price(8457.5).build(),
                            Product.builder().name("product9").quantity(45).price(84585.5).build(),
                            Product.builder().name("product10").quantity(5).price(854545.5).build(),
                            Product.builder().name("product11").quantity(15).price(8457.5).build(),
                            Product.builder().name("product12").quantity(45).price(84585.5).build()
                    )
            );
            productRepository.findAll().forEach(product ->
                    System.out.println(product));
        };
    }
}
