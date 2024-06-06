package com.excoder.orderservice.repository;

import com.excoder.orderservice.model.Order;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends MongoRepository<Order, Integer> {

    Optional<Order> findByOrderId(Integer orderId);

    List<Order> findByStatus(String status);

    List<Order> findByCreatedDateAfter(@Param("date") LocalDate createdDate);

    Optional<Order> findByCustomerId(Integer customerId);

    void deleteByOrderId(Integer orderId);

    void deleteByCustomerId(Integer customerId);
}
