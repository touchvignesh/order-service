package com.excoder.orderservice.service;

import com.excoder.orderservice.component.OrderConverter;
import com.excoder.orderservice.dto.OrderDTO;
import com.excoder.orderservice.model.Order;
import com.excoder.orderservice.repository.OrderRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    OrderConverter orderConverter;

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String orderTopic;

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Integer id) {
        return orderRepository.findByOrderId(id);
    }

    public Optional<Order> findByCustomerId(Integer customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public OrderDTO save(OrderDTO orderDTO) {
        Order order = orderConverter.convertOrderDtoToEntity(orderDTO);
        order = orderRepository.save(order);
        var successMessage = kafkaTemplate.send(orderTopic, order);
        successMessage.whenComplete((sendResult, exception) -> {
            if (exception != null) {
                successMessage.completeExceptionally(exception);
            } else {
                successMessage.complete(sendResult);
            }
            log.info(String.valueOf(sendResult));
        });
        return orderConverter.convertOrderEntityToDto(order);
    }

    public void deleteById(Integer id) {
        orderRepository.deleteByOrderId(id);
    }

    public void deleteByCustomerId(Integer customerId) {
        orderRepository.deleteByCustomerId(customerId);
    }

    public List<Order> findByStatus(String status) {
        return orderRepository.findByStatus(status);
    }

    public List<Order> findByCreatedDateAfter(LocalDate createdDate) {
        return orderRepository.findByCreatedDateAfter(createdDate);
    }
}
