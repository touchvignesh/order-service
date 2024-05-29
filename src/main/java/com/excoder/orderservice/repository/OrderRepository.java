package com.excoder.orderservice.repository;

import com.excoder.orderservice.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, Integer> {

    List<Order> findByStatus(String status);

    List<Order> findByCreatedDateAfter(@Param("date") LocalDate createdDate);

    Optional<Order> findByCustomerId(Integer customerId);

    void deleteByCustomerId(Integer customerId);

}