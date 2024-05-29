package com.excoder.orderservice.service;

import com.excoder.orderservice.model.Order;
import com.excoder.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String orderTopic;

    @Autowired
    private OrderRepository OrderRepository;

    public List<Order> findAll() {
        return OrderRepository.findAll();
    }

    public Optional<Order> findById(Integer id) {
        return OrderRepository.findById(id);
    }

    public Optional<Order> findByCustomerId(Integer customerId) {
        return OrderRepository.findByCustomerId(customerId);
    }

    public Order save(Order order) {
        OrderRepository.save(order);
        var message_success = kafkaTemplate.send(orderTopic, order);
        message_success.whenComplete((sendResult, exception) -> {
            if (exception != null) {
                message_success.completeExceptionally(exception);
            } else {
                message_success.complete(sendResult);
            }
            System.out.println(sendResult);
        });
        return order;
    }

    public void deleteById(Integer id) {
        OrderRepository.deleteById(id);
    }

    public void deleteByCustomerId(Integer customerId) {
        OrderRepository.deleteByCustomerId(customerId);
    }
    public List<Order> findByStatus(String status) {
        return OrderRepository.findByStatus(status);
    }

    public List<Order> findByCreatedDateAfter(LocalDate createdDate) {
        return OrderRepository.findByCreatedDateAfter(createdDate);
    }
}
