package com.excoder.orderservice;

import com.excoder.orderservice.model.Order;
import com.excoder.orderservice.repository.OrderRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private static final Logger log = LoggerFactory.getLogger(MainApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Autowired
    OrderRepository orderRepository;

    // Run this if app.db.init.enabled = true
    @Bean
    @ConditionalOnProperty(prefix = "app", name = "db.init.enabled", havingValue = "true")
    public CommandLineRunner demoCommandLineRunner() {
        return args -> {
            log.info("Running");

            Order p1 =
                    new Order(1, 1001, "Created", "Address 1", BigDecimal.valueOf(39999.00), LocalDate.of(2023, 8, 31));
            Order p2 = new Order(
                    2, 1002, "Updated", "Address 2", BigDecimal.valueOf(49999.00), LocalDate.of(2023, 10, 31));
            Order p3 = new Order(
                    3, 1003, "Rejected", "Address 3", BigDecimal.valueOf(59999.00), LocalDate.of(2023, 12, 31));
            Order p4 = new Order(
                    4, 1004, "Out for delivery", "Address 4", BigDecimal.valueOf(69999.00), LocalDate.of(2024, 05, 30));

            orderRepository.saveAll(List.of(p1, p2, p3, p4));
        };
    }
}
