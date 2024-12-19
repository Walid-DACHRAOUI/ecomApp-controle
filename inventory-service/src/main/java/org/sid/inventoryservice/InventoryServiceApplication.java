package org.sid.inventoryservice;

import lombok.Builder;
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
    CommandLineRunner start(ProductRepository productRepository, RepositoryRestConfiguration restConfiguration)
    {
        return args -> {
            restConfiguration.exposeIdsFor(Product.class);
            productRepository.saveAll(
                    List.of(
                            Product.builder().name("Computer").quantity(69).price(19000).build(),
                            Product.builder().name("Printer").quantity(100).price(499).build(),
                            Product.builder().name("Headphone").quantity(250).price(2000).build(),
                            Product.builder().name("Smartphone").quantity(106).price(9000).build()
                    )
            );
            productRepository.findAll().forEach(System.out::println);
        };
    }
}
