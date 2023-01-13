package org.sid.customerservice;

import org.sid.customerservice.entities.Customer;
import org.sid.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner start(CustomerRepository customerRepository , RepositoryRestConfiguration restConfiguration){
        return args -> {
            restConfiguration.exposeIdsFor(Customer.class);
            customerRepository.saveAll(
                    List.of(
                            Customer.builder().name("hmed").email("hmed@gmail.com").build(),
                            Customer.builder().name("hamza").email("hamza@gmail.com").build(),
                            Customer.builder().name("imane").email("imane@gmail.com").build(),
                            Customer.builder().name("cl1").email("hmed@gmail.com").build(),
                            Customer.builder().name("cl2").email("hamza@gmail.com").build(),
                            Customer.builder().name("cl3").email("imane@gmail.com").build(),
                            Customer.builder().name("cl4").email("hmed@gmail.com").build(),
                            Customer.builder().name("cl5").email("hamza@gmail.com").build(),
                            Customer.builder().name("cl6").email("imane@gmail.com").build()

                    )
            );
            customerRepository.findAll().forEach(customer ->
                    System.out.println(customer));
        };
    }
}
